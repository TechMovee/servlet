package org.example.projecttechmovee.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projecttechmovee.ClasseTabelas.Responsaveis;
import org.example.projecttechmovee.ClasseTabelasDAO.ResponsavelDAO;
import org.example.projecttechmovee.DbConexao.Conexao;
import org.example.projecttechmovee.Regex.Regex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Respon", value = "/Respon")
public class ServletResponsavel extends HttpServlet {
    // Instancia os DAOs e a classe de validação
    private final ResponsavelDAO crudResponsavel = new ResponsavelDAO(new Conexao());
    private final Regex validation = new Regex();

    // Lida com requisições GET para listar ou buscar responsável por CPF
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cpf = req.getParameter("cpf");
        List<Responsaveis> responsaveis;

        // Se não fornecer CPF, lista todos os responsáveis
        if (cpf == null || cpf.isEmpty()) {
            responsaveis = this.crudResponsavel.listarResponsaveis();
            if (responsaveis != null) {
                req.setAttribute("resps", responsaveis);
            } else {
                req.setAttribute("erro", "Não foi encontrado nenhum responsável.");
            }
            req.getRequestDispatcher("/AreaRestrita/Responsaveis/areaRestritaResponsavel.jsp").forward(req, resp);
        } else {
            // Caso tenha um CPF, busca o responsável correspondente
            if (this.validation.verificarCPF(cpf)) {
                responsaveis = new ArrayList<>();
                if (this.crudResponsavel.buscarResponsavelPorCpf(cpf) != null) {
                    responsaveis.add(this.crudResponsavel.buscarResponsavelPorCpf(cpf));
                    req.setAttribute("responsaveis", responsaveis);
                    req.getRequestDispatcher("/AreaRestrita/Responsaveis/areaRestritaResponsavel.jsp").forward(req, resp);
                } else {
                    req.setAttribute("erro", "Não foi encontrado nenhum responsável com esse CPF.");
                }
            }else{
                req.setAttribute("erro", "O CPF é inválido.");
            }
            req.getRequestDispatcher("/AreaRestrita/Responsaveis/areaRestritaResponsavelId.jsp").forward(req, resp);
        }
    }

    // Lida com requisições POST para inserir ou executar PUT/DELETE
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        // Se for "delete" ou "put", chama os métodos correspondentes
        if (method != null) {
            if (method.equals("delete")) {
                doDelete(req, resp);
            } else if (method.equals("put")) {
                doPut(req, resp);
            }
        } else {
            // Processa a inserção de um novo responsável
            String cpf = req.getParameter("cpfInserir");
            String nome = req.getParameter("nome");
            String dtNascimento = req.getParameter("dtNascimento");
            String foto = req.getParameter("foto");
            String senha = req.getParameter("senha");
            String email = req.getParameter("email");

            // Valida os campos antes de adicionar
            if (this.validation.verificarCPF(cpf) && this.validation.verificarData(dtNascimento) &&
                    this.validation.verificarEmail(email) && this.validation.verificarNome(nome) &&
                    this.validation.verificarFoto(foto) && this.validation.verificarPassword(senha)) {

                // Cria um novo responsável e adiciona ao banco
                Responsaveis responsaveisAdicionar = new Responsaveis(cpf, nome, dtNascimento, foto, senha, email);
                int resultado = this.crudResponsavel.adicionarResponsavel(responsaveisAdicionar);
                if (resultado > 0) {
                    doGet(req, resp); // Redireciona para a listagem após a inserção
                } else {
                    req.setAttribute("erro", "Não foi possível inserir um responsável.");
                }
            }
            req.getRequestDispatcher("/AreaRestrita/Responsaveis/areaRestritaResponsavelInserir.jsp").forward(req, resp);
        }
    }

    // Lida com requisições PUT para atualizar dados de um responsável
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cpf = req.getParameter("cpfAtualizar");
        String nome = req.getParameter("nome");
        String dtNascimento = req.getParameter("dtNascimento");
        String foto = req.getParameter("foto");
        String senha = req.getParameter("senha");
        String email = req.getParameter("email");

        // Valida os dados antes de atualizar
        if (this.validation.verificarPassword(senha) && this.validation.verificarFoto(foto) &&
                this.validation.verificarEmail(email) && this.validation.verificarNome(nome) &&
                this.validation.verificarData(dtNascimento) && this.validation.verificarCPF(cpf)) {

            // Cria um responsável atualizado e chama o método de atualização
            Responsaveis responsaveisAtualizada = new Responsaveis(cpf, nome, dtNascimento, foto, senha, email);
            int retornoLinhas = this.crudResponsavel.atualizarResponsavel(responsaveisAtualizada);
            if (retornoLinhas > 0) {
                doGet(req, resp);
            } else if (retornoLinhas == 0) {
                req.setAttribute("erro", "Não foi possível encontrar um responsável com esse CPF.");
            } else {
                req.setAttribute("erro", "Não foi possível atualizar o responsável.");
            }
        } else {
            req.setAttribute("erro", "Informações Incorretas.");
        }
        req.getRequestDispatcher("/AreaRestrita/Responsaveis/areaRestritaResponsavelAtualizar.jsp").forward(req, resp);
    }

    // Lida com requisições DELETE para remover um responsável pelo CPF
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cpf = req.getParameter("cpfDeletar");
        int resultado;

        // Verifica se o CPF é válido
        if (this.validation.verificarCPF(cpf)) {
            resultado = this.crudResponsavel.deletarResponsavel(cpf);
            if (resultado > 0) {
                doGet(req, resp); // Redireciona após a exclusão
            } else if (resultado == 0) {
                req.setAttribute("erro", "Não foi encontrado um responsável com esse CPF.");
            } else {
                req.setAttribute("erro", "Não foi possível deletar um responsável, tente novamente mais tarde.");
            }
        } else {
            req.setAttribute("erro", "O CPF é inválido.");
        }
        req.getRequestDispatcher("/AreaRestrita/Responsaveis/areaRestritaResponsavelDeletar.jsp").forward(req, resp);
    }
}
