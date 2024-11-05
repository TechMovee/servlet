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

    // Instância do DAO para operações com Admin
    private AdminDAO crudAdmin = new AdminDAO(new Conexao());

    // Método para tratar requisições GET
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtém o parâmetro id da requisição
        String id = req.getParameter("id");

        // Se o ID não foi fornecido ou está vazio
        if (id==null || id.isEmpty()) {
            // Lista todos os administradores
            List<Admin> admins = this.crudAdmin.listarAdmins();
            // Se houver administradores, envia para a página JSP
            if (admins != null) {
                req.setAttribute("admins", admins);
                req.getRequestDispatcher("AreaRestrita/Administrador/areaRestritaAdmin.jsp").forward(req, resp);
            } else {
                // Se não houver administradores, define uma mensagem de erro
                req.setAttribute("erro", "Não tem nenhum administrador.");
            }
        } else {
            // Caso contrário, tenta buscar um administrador pelo ID
            List<Admin> admins = new ArrayList<>();
            try {
                // Verifica se o administrador existe
                if (this.crudAdmin.buscarAdmin(Integer.parseInt(id)) != null) {
                    // Adiciona o administrador encontrado à lista
                    admins.add(this.crudAdmin.buscarAdmin(Integer.parseInt(id)));
                    // Envia a lista para a página JSP
                    req.setAttribute("admins", admins);
                    req.getRequestDispatcher("/AreaRestrita/Administrador/areaRestritaAdmin.jsp").forward(req, resp);
                } else {
                    // Define uma mensagem de erro se o ID não for encontrado
                    req.setAttribute("erro", "Não tem nenhum administrador com esse ID.");
                }
            } catch (NumberFormatException nfe) {
                // Define uma mensagem de erro se o ID não for um número válido
                req.setAttribute("erro", "O ID deve ser um número.");
            }
        }
        // Redireciona para a página JSP
        req.getRequestDispatcher("/AreaRestrita/Administrador/areaRestritaAdmin.jsp").forward(req, resp);
    }

    // Método para tratar requisições POST
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int resultado;
        // Obtém o método da requisição
        String method = req.getParameter("method");

        // Verifica qual método foi solicitado (delete ou put)
        if (method != null) {
            if (method.equals("delete")) {
                // Chama o método para deletar um administrador
                doDelete(req, resp);
            } else if (method.equals("put")) {
                // Chama o método para atualizar um administrador
                doPut(req, resp);
            }
        } else {
            // Obtém os valores do formulário
            String nome = req.getParameter("nome");
            String email = req.getParameter("email");
            String password = req.getParameter("senha");
            String confirmar = req.getParameter("confirmar");

            // Verifica se a senha e a confirmação são iguais
            if (password.equals(confirmar)) {
                System.out.println(this.crudAdmin.buscarAdmin(email));

                // Verifica se já existe um administrador com o email fornecido
                if (this.crudAdmin.buscarAdmin(email) == null) {
                    // Cria um novo administrador e tenta adicioná-lo
                    Admin adminAdicionado = new Admin(nome, email, password);
                    resultado = this.crudAdmin.adicionarAdmin(adminAdicionado);

                    // Se a adição for bem-sucedida, chama o método doGet
                    if (resultado > 0) {
                        doGet(req, resp);
                    } else {
                        // Define uma mensagem de erro se a adição falhar
                        req.setAttribute("erro", "Não foi possivel inserir um administrador.");
                    }
                } else {
                    // Define uma mensagem de erro se o email já estiver em uso
                    req.setAttribute("erro", "Já existe um administrador com esse email.");
                }
            } else {
                // Define uma mensagem de erro se as senhas não coincidirem
                req.setAttribute("erro", "A senha deve ser a mesma da anterior.");
            }
            // Redireciona para a página de inserção de administrador
            req.getRequestDispatcher("/AreaRestrita/Administrador/areaRestritaAdminInserir.jsp").forward(req, resp);
        }
    }

    // Método para atualizar um administrador
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtém o ID do administrador a ser atualizado
        String id = req.getParameter("idAtualizar");
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        try {
            // Busca o administrador pelo ID
            Admin adminAtualizado = this.crudAdmin.buscarAdmin(Integer.parseInt(id));
            if (adminAtualizado != null) {
                // Atualiza os dados do administrador
                adminAtualizado.setEmail(email);
                adminAtualizado.setName(nome);
                adminAtualizado.setSenha(senha);

                // Tenta atualizar o administrador no banco de dados
                int retornoLinhas = this.crudAdmin.atualizarAdmin(adminAtualizado);
                // Se a atualização for bem-sucedida, chama o método doGet
                if (retornoLinhas > 0) {
                    doGet(req, resp);
                } else {
                    // Define uma mensagem de erro se a atualização falhar
                    req.setAttribute("erro", "Não foi possivel atualizar o administrador.");
                }
            } else {
                // Define uma mensagem de erro se o administrador não for encontrado
                req.setAttribute("erro", "Não foi possivel achar um administrador com esse ID.");
            }
            req.setAttribute("erro", "Informações incorretas.");
        } catch (NumberFormatException nfe) {
            // Define uma mensagem de erro se o ID não for um número
            req.setAttribute("erro", "O ID deve ser um número");
        }
        // Redireciona para a página de atualização de administrador
        req.getRequestDispatcher("/AreaRestrita/Administrador/areaRestritaAdminAtualizar.jsp").forward(req, resp);
    }

    // Método para deletar um administrador
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idOuEmail = req.getParameter("idOuEmail");
        int resultado;

        try {
            // Verifica se é um número válido (ID)
            if (Integer.parseInt(idOuEmail) > 0) {
                resultado = this.crudAdmin.deletarAdmin(Integer.parseInt(idOuEmail));
                // Se a deleção for bem-sucedida, chama o método doGet
                if (resultado > 0) {
                    doGet(req, resp);
                } else if (resultado == 0) {
                    // Define uma mensagem de erro se o ID não for encontrado
                    req.setAttribute("erro", "Não foi encontrado um administrador com esse ID.");
                } else {
                    // Define uma mensagem de erro se a deleção falhar
                    req.setAttribute("erro", "Não foi possivel deletar um administrador, tente novamente mais tarde.");
                }
            } else {
                // Define uma mensagem de erro se o ID for menor ou igual a 0
                req.setAttribute("erro", "O ID deve ser maior que 0.");
            }
        } catch (NumberFormatException nfe) {
            // Caso não seja número, verifica se é um e-mail válido
            if (this.crudAdmin.buscarAdmin(idOuEmail) != null) {
                resultado = this.crudAdmin.deletarAdmin(idOuEmail);
            } else {
                // Define uma mensagem de erro se não existir um administrador com o email fornecido
                req.setAttribute("erro","Não existe um administrador com esse email.");
            }
        }
        // Redireciona para a página de deleção de administrador
        req.getRequestDispatcher("/AreaRestrita/Administrador/areaRestritaAdminDeletar.jsp").forward(req, resp);
    }
}