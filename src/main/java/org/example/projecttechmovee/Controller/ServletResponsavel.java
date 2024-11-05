package org.example.projecttechmovee.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projecttechmovee.ClasseTabelas.Escolas;
import org.example.projecttechmovee.ClasseTabelas.Responsaveis;
import org.example.projecttechmovee.ClasseTabelasDAO.ResponsavelDAO;
import org.example.projecttechmovee.DbConexao.Conexao;
import org.example.projecttechmovee.Regex.Regex;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Respon", value = "/Respon")
public class ServletResponsavel extends HttpServlet {
    // Instância da classe ResponsavelDAO para interagir com os dados dos responsáveis
    private final ResponsavelDAO crudResponsavel = new ResponsavelDAO(new Conexao());
    // Instância da classe Regex para validações de dados
    private final Regex validation = new Regex();

    // Método doGet para lidar com as requisições GET
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cpf = req.getParameter("cpf"); // Obtém o CPF do parâmetro da requisição
        if (cpf == null) {
            // Se CPF não for fornecido, lista todos os responsáveis
            List<Responsaveis> responsaveis = this.crudResponsavel.listarResponsaveis();
            if (responsaveis != null) {
                req.setAttribute("resps", responsaveis); // Adiciona a lista de responsáveis ao atributo da requisição
            } else {
                req.setAttribute("erro", "Não foi encontrado nenhum responsavel."); // Mensagem de erro se nenhum responsável for encontrado
            }
            req.getRequestDispatcher("/AreaRestrita/Responsaveis/areaRestritaResponsavel.jsp").forward(req, resp); // Redireciona para a página
        } else {
            // Se CPF for fornecido, busca um responsável específico
            List<Responsaveis> responsaveis = new ArrayList<>();
            if (this.crudResponsavel.buscarResponsavelPorCpf(cpf) != null) {
                responsaveis.add(this.crudResponsavel.buscarResponsavelPorCpf(cpf)); // Adiciona o responsável encontrado à lista
                req.setAttribute("responsaveis", responsaveis); // Adiciona o responsável ao atributo da requisição
            } else {
                req.setAttribute("erro", "Não foi encontrado nenhum responsável com esse CPF."); // Mensagem de erro se não encontrar
            }
            req.getRequestDispatcher("/AreaRestrita/Responsaveis/areaRestritaResponsavel.jsp").forward(req, resp); // Redireciona para a página
        }
    }

    // Método doPost para lidar com as requisições POST
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method"); // Obtém o método da requisição
        if (method != null) {
            // Se o método for delete, chama o método doDelete
            if (method.equals("delete")) {
                doDelete(req, resp);
                // Se o método for put, chama o método doPut
            } else if (method.equals("put")) {
                doPut(req, resp);
            }
        } else {
            // Pegando os valores do formulário da requisição
            String cpf = req.getParameter("cpfInserir");
            String nome = req.getParameter("nome");
            String dtNascimento = req.getParameter("dtNascimento");
            String foto = req.getParameter("foto");
            String senha = req.getParameter("senha");
            String email = req.getParameter("email");

            // Valida todos os campos antes de adicionar um novo responsável
            if (this.validation.verificarCPF(cpf) && this.validation.verificarData(dtNascimento) &&
                    this.validation.verificarEmail(email) && this.validation.verificarNome(nome) &&
                    this.validation.verificarFoto(foto) && this.validation.verificarPassword(senha)) {
                Responsaveis responsaveisAdicionar = new Responsaveis(cpf, nome, dtNascimento, foto, senha, email);
                int resultado = this.crudResponsavel.adicionarResponsavel(responsaveisAdicionar); // Adiciona o responsável ao banco de dados
                if (resultado > 0) {
                    doGet(req, resp); // Se adicionado com sucesso, chama doGet para atualizar a lista
                } else {
                    req.setAttribute("erro", "Não foi possivel inserir um responsável."); // Mensagem de erro se falhar
                }
            }
            req.getRequestDispatcher("/AreaRestrita/Responsaveis/areaRestritaResponsavelInserir.jsp").forward(req, resp); // Redireciona para a página de inserir
        }
    }

    // Método doPut para atualizar um responsável
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cpf = req.getParameter("cpfAtualizar");
        String nome = req.getParameter("nome");
        String dtNascimento = req.getParameter("dtNascimento");
        String foto = req.getParameter("foto");
        String senha = req.getParameter("senha");
        String email = req.getParameter("email");

        // Valida todos os campos antes de atualizar o responsável
        if (this.validation.verificarPassword(senha) && this.validation.verificarFoto(foto) &&
                this.validation.verificarEmail(email) && this.validation.verificarNome(nome) &&
                this.validation.verificarData(dtNascimento) && this.validation.verificarCPF(cpf)) {
            Responsaveis responsaveisAtualizada = new Responsaveis(cpf, nome, dtNascimento, foto, senha, email);
            int retornoLinhas = this.crudResponsavel.atualizarResponsavel(responsaveisAtualizada); // Atualiza o responsável no banco de dados
            if (retornoLinhas > 0) {
                doGet(req, resp); // Se atualizado com sucesso, chama doGet para atualizar a lista
            } else if (retornoLinhas == 0) {
                req.setAttribute("erro", "Não foi possivel encontrar um responsável com esse CPF."); // Mensagem de erro se não encontrar
            } else {
                req.setAttribute("erro", "Não foi possivel atualizar um responável."); // Mensagem de erro se falhar
            }
        } else {
            req.setAttribute("erro", "Informações Incorretas."); // Mensagem de erro se a validação falhar
        }
        req.getRequestDispatcher("/AreaRestrita/Responsaveis/areaRestritaResponsavelAtualizar.jsp").forward(req, resp); // Redireciona para a página de atualização
    }

    // Método doDelete para deletar um responsável
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cpf = req.getParameter("cpfDeletar");
        int resultado;

        // Verifica se é um CPF válido
        if (this.validation.verificarCPF(cpf)) {
            resultado = this.crudResponsavel.deletarResponsavel(cpf); // Tenta deletar o responsável
            if (resultado > 0) {
                doGet(req, resp); // Se deletado com sucesso, chama doGet para atualizar a lista
            } else if (resultado == 0) {
                req.setAttribute("erro", "Não foi encontrado um responsável com esse CPF."); // Mensagem de erro se não encontrar
            } else {
                req.setAttribute("erro", "Não foi possivel deletar um responsável, tente novamente mais tarde."); // Mensagem de erro se falhar
            }
        } else {
            req.setAttribute("erro", "O CPF é inválido."); // Mensagem de erro se CPF for inválido
        }
        req.getRequestDispatcher("/AreaRestrita/Responsaveis/areaRestritaResponsavelDeletar.jsp").forward(req, resp); // Redireciona para a página de deletar
    }
}