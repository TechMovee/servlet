package org.example.projecttechmovee.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projecttechmovee.ClasseTabelas.Escolas;
import org.example.projecttechmovee.ClasseTabelasDAO.EscolaDAO;
import org.example.projecttechmovee.DbConexao.Conexao;
import org.example.projecttechmovee.Regex.Regex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Escola", value = "/Escola")
public class ServletEscola extends HttpServlet {

    // Instância do DAO para operações com Escola
    private final EscolaDAO crudEscola = new EscolaDAO(new Conexao());
    // Instância da classe de validação
    private final Regex validation = new Regex();

    // Método para tratar requisições GET
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // Obtém o parâmetro "id" da requisição
        String id = req.getParameter("id");

        // Se o ID não foi fornecido ou está vazio
        if (id == null || id.isEmpty()) {
            // Lista todas as escolas
            List<Escolas> escolas = this.crudEscola.listarEscolas();
            // Se houver escolas, envia para a página JSP
            if (escolas != null) {
                req.setAttribute("escolas", escolas);
                req.getRequestDispatcher("/AreaRestrita/Escola/areaRestritaEscola.jsp").forward(req, resp);
            } else {
                // Se não houver escolas, define uma mensagem de erro
                req.setAttribute("erro", "Não foi encontrado nenhuma Escola.");
            }
        } else {
            // Caso contrário, tenta buscar uma escola pelo ID
            List<Escolas> escolas = new ArrayList<>();
            try {
                // Verifica se a escola existe
                if (this.crudEscola.buscarEscolaPorId(Integer.parseInt(id)) != null) {
                    // Adiciona a escola encontrada à lista
                    escolas.add(this.crudEscola.buscarEscolaPorId(Integer.parseInt(id)));
                    // Envia a lista para a página JSP
                    req.setAttribute("escolas", escolas);
                    req.getRequestDispatcher("/AreaRestrita/Escola/areaRestritaEscola.jsp").forward(req, resp);
                } else {
                    // Define uma mensagem de erro se o ID não for encontrado
                    req.setAttribute("erro", "Não tem nenhuma escola com esse ID.");
                }
            } catch (NumberFormatException nfe) {
                // Define uma mensagem de erro se o ID não for um número válido
                req.setAttribute("erro", "O ID deve ser um número");
            }
        }
        // Redireciona para a página JSP
        req.getRequestDispatcher("/AreaRestrita/Escola/areaRestritaEscola.jsp").forward(req, resp);
    }

    // Método para tratar requisições POST
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtém o método da requisição
        String method = req.getParameter("method");

        // Verifica qual método foi solicitado (delete ou put)
        if (method != null) {
            if (method.equals("delete")) {
                // Chama o método para deletar uma escola
                doDelete(req, resp);
            } else if (method.equals("put")) {
                // Chama o método para atualizar uma escola
                doPut(req, resp);
            }
        } else {
            // Obtém o nome da escola enviado pelo formulário
            String nome= req.getParameter("nome");

            // Verifica se o nome da escola é válido
            if (this.validation.verificarNome(nome)) {
                // Cria uma nova escola e tenta adicioná-la
                Escolas escolaAdicionada = new Escolas(nome);
                int resultado = this.crudEscola.adicionarEscola(escolaAdicionada);
                // Se a adição for bem-sucedida, chama o método doGet
                if (resultado > 0) {
                    doGet(req, resp);
                } else {
                    // Define uma mensagem de erro se a adição falhar
                    req.setAttribute("erro", "Não foi possivel inserir uma Escola.");
                }
            } else {
                // Define uma mensagem de erro se o nome for inválido
                req.setAttribute("erro", "O nome é inválido.");
            }
        }
        // Redireciona para a página de inserção de escola
        req.getRequestDispatcher("/AreaRestrita/Escola/areaRestritaEscolaInserir.jsp").forward(req, resp);
    }

    // Método para atualizar uma escola
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtém o ID da escola a ser atualizada
        String id = req.getParameter("idAtualizar");
        String nome = req.getParameter("nome");

        try {
            // Verifica se o nome da escola é válido
            if (this.validation.verificarNome(nome)) {
                // Cria uma escola atualizada
                Escolas escolaAtualizada = new Escolas(Integer.parseInt(id), nome);
                // Tenta atualizar a escola no banco de dados
                int retornoLinhas = this.crudEscola.atualizarEscola(escolaAtualizada);
                // Se a atualização for bem-sucedida, chama o método doGet
                if (retornoLinhas > 0) {
                    doGet(req, resp);
                } else {
                    // Define uma mensagem de erro se a atualização falhar
                    req.setAttribute("erro", "Não foi possivel atualizar a Escola.");
                }
            } else {
                // Define uma mensagem de erro se o nome for inválido
                req.setAttribute("erro", "Informações incorretas.");
            }
        } catch (NumberFormatException nfe) {
            // Define uma mensagem de erro se o ID não for um número
            req.setAttribute("erro", "O ID deve ser um número.");
        }
        // Redireciona para a página de atualização de escola
        req.getRequestDispatcher("/AreaRestrita/Escola/areaRestritaEscolaAtualizar.jsp").forward(req, resp);
    }

    // Método para deletar uma escola
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtém o ID da escola a ser deletada
        String id = req.getParameter("idDeletar");
        int resultado;

        try {
            // Verifica se é um número válido (ID)
            if (Integer.parseInt(id) > 0) {
                resultado = this.crudEscola.removerEscola(Integer.parseInt(id));
                // Se a deleção for bem-sucedida, chama o método doGet
                if (resultado > 0) {
                    doGet(req, resp);
                } else if (resultado == 0) {
                    // Define uma mensagem de erro se o ID não for encontrado
                    req.setAttribute("erro", "Não foi encontrado uma escola com esse ID.");
                } else {
                    // Define uma mensagem de erro se a deleção falhar
                    req.setAttribute("erro", "Não foi possivel deletar uma escola, tente novamente mais tarde.");
                }
            } else {
                // Define uma mensagem de erro se o ID for menor ou igual a 0
                req.setAttribute("erro", "O ID deve ser maior que 0.");
            }
        } catch (NumberFormatException nfe) {
            // Define uma mensagem de erro se o ID não for um número
            req.setAttribute("erro", "O ID deve ser um número.");
        }
        // Redireciona para a página de deleção de escola
        req.getRequestDispatcher("/AreaRestrita/Escola/areaRestritaEscolaDeletar.jsp").forward(req, resp);
    }
}

