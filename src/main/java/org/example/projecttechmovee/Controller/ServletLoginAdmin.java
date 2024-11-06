package org.example.projecttechmovee.Controller;

import java.io.*;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projecttechmovee.ClasseTabelas.Admin;
import org.example.projecttechmovee.ClasseTabelasDAO.AdminDAO;
import org.example.projecttechmovee.DbConexao.Conexao;
import org.example.projecttechmovee.Regex.Regex;

@WebServlet(name = "loginAdm", value = "/loginAdm")
public class ServletLoginAdmin extends HttpServlet {

    private Conexao conexao;
//    Método doPost para tratar as requisições, enviadas pelo usuário através do formulários.
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

//      Instância um Objeto da AdminDAO
        AdminDAO crudAdmin = new AdminDAO(new Conexao());

//      Pegando os valores de email e password enviado pelo o usuário no formulário da requisição.
        String email = req.getParameter("email");
        String password = req.getParameter("password");


        //Verificando se os dados inserios pelo usuário são validos.
        try {
            Admin admin = crudAdmin.buscarAdmin(email);
            if (admin != null && admin.getSenha().equals(password)) {
                //            Redireciona o usuário para a Área Restrita
                req.setAttribute("passouPorServlet", "Passou pelo Servlet.");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/AreaRestrita/TelaInicial/areaRestrita.jsp");
                dispatcher.forward(req, resp);
            }else{
                //          Redireciona o usuário para a página de erro.
                req.setAttribute("erro", "Email e/ou Senha incorretos");
                req.getRequestDispatcher("/AreaRestrita/Login/LoginAreaRestrita.jsp").forward(req, resp);
            };
        }catch (NullPointerException npe){
            req.setAttribute("erro", "Email e/ou Senha incorretos");
            req.getRequestDispatcher("/AreaRestrita/Login/LoginAreaRestrita.jsp").forward(req, resp);
        }
    }
}