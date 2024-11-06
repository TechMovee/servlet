package org.example.projecttechmovee.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projecttechmovee.ClasseTabelas.Admin;
import org.example.projecttechmovee.ClasseTabelasDAO.AdminDAO;
import org.example.projecttechmovee.DbConexao.Conexao;
import org.example.projecttechmovee.Regex.Regex;

import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "Admin", value = "/Admin")
public class ServletAdmin extends HttpServlet {

    private AdminDAO crudAdmin = new AdminDAO(new Conexao());
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id==null || id.isEmpty()) {
            List<Admin> admins = this.crudAdmin.listarAdmins();
            if (admins != null) {
                req.setAttribute("admins", admins);
                req.getRequestDispatcher("AreaRestrita/Administrador/areaRestritaAdmin.jsp").forward(req, resp);
            }else{
                req.setAttribute("erro", "Não tem nenhum administrador.");
            }
        }else{
            List<Admin> admins = new ArrayList<>();
            try {
                if (this.crudAdmin.buscarAdmin(Integer.parseInt(id)) != null) {
                    admins.add(this.crudAdmin.buscarAdmin(Integer.parseInt(id)));
                    req.setAttribute("admins", admins);
                    req.getRequestDispatcher("/AreaRestrita/Administrador/areaRestritaAdmin.jsp").forward(req, resp);
                } else {
                    req.setAttribute("erro", "Não tem nenhum administrador com esse ID.");
                }
            }catch (NumberFormatException nfe){
                req.setAttribute("erro", "O ID deve ser um número.");
            }
        }
        req.getRequestDispatcher("/AreaRestrita/Administrador/areaRestritaAdmin.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int resultado;
        String method = req.getParameter("method");
        if (method != null) {
            if (method.equals("delete")) {
                doDelete(req, resp);
            } else if (method.equals("put")) {
                doPut(req, resp);
            }
        }else{

//      Pegando os valores de email e password enviado pelo o usuário no formulário da requisição.
            String nome = req.getParameter("nome");
            String email = req.getParameter("email");
            String password = req.getParameter("senha");
            String confirmar = req.getParameter("confirmar");
            if (password.equals(confirmar)) {
                System.out.println(this.crudAdmin.buscarAdmin(email));
                if (this.crudAdmin.buscarAdmin(email) == null) {
                    Admin adminAdicionado = new Admin(nome, email, password);
                    resultado = this.crudAdmin.adicionarAdmin(adminAdicionado);
                    if (resultado > 0) {
                        doGet(req, resp);
                    } else {
                        req.setAttribute("erro", "Não foi possivel inserir um administrador.");
                    }
                }else{
                    req.setAttribute("erro", "Já existe um administrador com esse email.");
                }
            } else {
                req.setAttribute("erro", "A senha deve ser a mesma da anterior.");
            }
            req.getRequestDispatcher("/AreaRestrita/Administrador/areaRestritaAdminInserir.jsp").forward(req, resp);
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("idAtualizar");
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        try {
            Admin adminAtualizado = this.crudAdmin.buscarAdmin(Integer.parseInt(id));
            if (adminAtualizado != null) {
                    adminAtualizado.setEmail(email);
                    adminAtualizado.setName(nome);
                    adminAtualizado.setSenha(senha);
                    int retornoLinhas = this.crudAdmin.atualizarAdmin(adminAtualizado);
                    if (retornoLinhas > 0) {
                        doGet(req, resp);
                    } else {
                        req.setAttribute("erro", "Não foi possivel atualizar o administrador.");
                    }
            }else{
                    req.setAttribute("erro", "Não foi possivel achar um administrador com esse ID.");
                }
            req.setAttribute("erro", "Informações incorretas.");
        }
        catch (NumberFormatException nfe){
            req.setAttribute("erro", "O ID deve ser um número");
        }
        req.getRequestDispatcher("/AreaRestrita/Administrador/areaRestritaAdminAtualizar.jsp").forward(req, resp);
    }



    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idOuEmail = req.getParameter("idOuEmail");
        int resultado;

        try {
            // Verifica se é um número válido (ID)
            if (Integer.parseInt(idOuEmail) > 0) {
                resultado = this.crudAdmin.deletarAdmin(Integer.parseInt(idOuEmail));
                if (resultado>0){
                    doGet(req, resp);
                }else if (resultado == 0){
                    req.setAttribute("erro", "Não foi encontrado um administrador com esse ID.");
                }else{
                    req.setAttribute("erro", "Não foi possivel deletar um administrador, tente novamente mais tarde.");
                }
            }else{
                req.setAttribute("erro", "O ID deve ser maior qu 0.");
            }
        }
        catch (NumberFormatException nfe) {
            // Caso não seja número, verifica se é um e-mail válido
            if (this.crudAdmin.buscarAdmin(idOuEmail) != null) {
                resultado = this.crudAdmin.deletarAdmin(idOuEmail);
            }else{
                req.setAttribute("erro","Não existe um administrador com esse email.");
                }
            }
        req.getRequestDispatcher("/AreaRestrita/Administrador/areaRestritaAdminDeletar.jsp").forward(req, resp);
    }
}
