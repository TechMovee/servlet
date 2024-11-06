package org.example.projecttechmovee.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projecttechmovee.ClasseTabelas.Admin;
import org.example.projecttechmovee.ClasseTabelasDAO.AdminDAO;
import org.example.projecttechmovee.DbConexao.Conexao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Admin", value = "/Admin")
public class ServletAdmin extends HttpServlet {

    private final AdminDAO crudAdmin = new AdminDAO(new Conexao());

    // Lida com requisições GET para listar ou buscar admins por ID
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        // Se não houver ID, lista todos os admins
        if (id == null || id.isEmpty()) {
            List<Admin> admins = crudAdmin.listarAdmins();
            req.setAttribute("admins", admins != null ? admins : new ArrayList<>());
            if (admins == null) req.setAttribute("erro", "Não tem nenhum administrador.");

            req.getRequestDispatcher("AreaRestrita/Administrador/areaRestritaAdmin.jsp").forward(req, resp);

        } else {
            // Busca admin específico pelo ID
            try {
                Admin admin = crudAdmin.buscarAdmin(Integer.parseInt(id));
                if (admin != null) {
                    req.setAttribute("admins", List.of(admin));
                } else {
                    req.setAttribute("erro", "Não tem nenhum administrador com esse ID.");
                }
            } catch (NumberFormatException e) {
                req.setAttribute("erro", "O ID deve ser um número.");
            }

            req.getRequestDispatcher("/AreaRestrita/Administrador/areaRestritaAdminId.jsp").forward(req, resp);
        }
    }

    // Lida com requisições POST para inserir ou executar métodos PUT/DELETE conforme "method"
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        // Se "method" for delete ou put, chama os métodos respectivos
        if (method != null) {
            if ("delete".equals(method)) {
                doDelete(req, resp);
            } else if ("put".equals(method)) {
                doPut(req, resp);
            }
        } else {
            // Inserção de um novo administrador
            String nome = req.getParameter("nome");
            String email = req.getParameter("email");
            String password = req.getParameter("senha");
            String confirmar = req.getParameter("confirmar");

            // Verifica se a senha e a confirmação correspondem
            if (password.equals(confirmar)) {
                // Verifica se já existe um admin com o email fornecido
                if (crudAdmin.buscarAdmin(email) == null) {
                    Admin adminAdicionado = new Admin(nome, email, password);
                    if (crudAdmin.adicionarAdmin(adminAdicionado) > 0) {
                        doGet(req, resp); // Redireciona para listagem após inserção bem-sucedida
                    } else {
                        req.setAttribute("erro", "Não foi possível inserir um administrador.");
                    }
                } else {
                    req.setAttribute("erro", "Já existe um administrador com esse email.");
                }
            } else {
                req.setAttribute("erro", "A senha deve ser a mesma da anterior.");
            }

            req.getRequestDispatcher("/AreaRestrita/Administrador/areaRestritaAdminInserir.jsp").forward(req, resp);
        }
    }

    // Lida com requisições PUT para atualizar dados do administrador
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("idAtualizar");
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        try {
            // Busca admin pelo ID e atualiza os dados
            Admin adminAtualizado = crudAdmin.buscarAdmin(Integer.parseInt(id));
            if (adminAtualizado != null) {
                adminAtualizado.setEmail(email);
                adminAtualizado.setName(nome);
                adminAtualizado.setSenha(senha);

                // Executa atualização e verifica o resultado
                if (crudAdmin.atualizarAdmin(adminAtualizado) > 0) {
                    doGet(req, resp);
                } else {
                    req.setAttribute("erro", "Não foi possível atualizar o administrador.");
                }
            } else {
                req.setAttribute("erro", "Não foi possível achar um administrador com esse ID.");
            }
        } catch (NumberFormatException e) {
            req.setAttribute("erro", "O ID deve ser um número.");
        }

        req.getRequestDispatcher("/AreaRestrita/Administrador/areaRestritaAdminAtualizar.jsp").forward(req, resp);
    }

    // Lida com requisições DELETE para remover um admin por ID
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idDeletar = req.getParameter("idDeletar");
        int resultado;

        try {
            // Verifica se é um número válido (ID)
            if (Integer.parseInt(idDeletar) > 0) {
                resultado = this.crudAdmin.deletarAdmin(Integer.parseInt(idDeletar));
                // Verifica o resultado da exclusão
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
            req.setAttribute("erro", "O ID deve ser um número.");
        }
        req.getRequestDispatcher("/AreaRestrita/Administrador/areaRestritaAdminDeletar.jsp").forward(req, resp);
    }
}
