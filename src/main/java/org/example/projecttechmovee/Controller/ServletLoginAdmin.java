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

    private Conexao conexao; // Instância da conexão com o banco de dados

    // Método doPost para tratar as requisições enviadas pelo usuário através do formulário
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        // Instância um objeto da AdminDAO para interagir com os dados do administrador
        AdminDAO crudAdmin = new AdminDAO(new Conexao());

        // Obtendo os valores de email e password enviados pelo usuário no formulário
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // Verificando se os dados inseridos pelo usuário são válidos
        try {
            // Busca o administrador pelo email
            Admin admin = crudAdmin.buscarAdmin(email);
            // Verifica se o administrador existe e se a senha está correta
            if (admin != null && admin.getSenha().equals(password)) {
                // Se as credenciais estiverem corretas, redireciona o usuário para a área restrita
                req.setAttribute("nome", admin.getName());
                req.setAttribute("passouPorServlet", "Passou pelo Servlet.");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/AreaRestrita/TelaInicial/areaRestrita.jsp");
                dispatcher.forward(req, resp);
            } else {
                // Se as credenciais estiverem incorretas, redireciona para a página de erro
                req.setAttribute("erro", "Email e/ou Senha incorretos");
                req.getRequestDispatcher("/AreaRestrita/Login/LoginAreaRestrita.jsp").forward(req, resp);
            }
        } catch (NullPointerException npe) {
            // Caso ocorra uma exceção, redireciona para a página de erro
            req.setAttribute("erro", "Email e/ou Senha incorretos");
            req.getRequestDispatcher("/AreaRestrita/Login/LoginAreaRestrita.jsp").forward(req, resp);
        }
    }
}