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
    private final ResponsavelDAO crudResponsavel = new ResponsavelDAO(new Conexao());
    private final Regex validation = new Regex();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String cpf = req.getParameter("cpf");
        if (cpf == null) {
            List<Responsaveis> responsaveis = this.crudResponsavel.listarResponsaveis();
            if (responsaveis != null) {
                req.setAttribute("resps", responsaveis);
            } else {
                req.setAttribute("erro", "Não foi encontrado nenhum responsavel.");
            }
            req.getRequestDispatcher("/AreaRestrita/Responsaveis/areaRestritaResponsavel.jsp").forward(req, resp);
        }else{
            List<Responsaveis> responsaveis = new ArrayList<>();
            if (this.crudResponsavel.buscarResponsavelPorCpf(cpf) != null) {
                responsaveis.add(this.crudResponsavel.buscarResponsavelPorCpf(cpf));
                req.setAttribute("responsaveis", responsaveis);
            } else {
                req.setAttribute("erro", "Não foi encontrado nenhum responsável com esse CPF.");
            }
            req.getRequestDispatcher("/AreaRestrita/Responsaveis/areaRestritaResponsavel.jsp").forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method != null) {
            if (method.equals("delete")) {
                doDelete(req, resp);
            } else if (method.equals("put")) {
                doPut(req, resp);
            }
        }else{

//      Pegando os valores de email e password enviado pelo o usuário no formulário da requisição.

            String cpf = req.getParameter("cpfInserir");
            String nome = req.getParameter("nome");
            String dtNascimento = req.getParameter("dtNascimento");
            String foto = req.getParameter("foto");
            String senha = req.getParameter("senha");
            String email = req.getParameter("email");

            if (this.validation.verificarCPF(cpf) && this.validation.verificarData(dtNascimento) && this.validation.verificarEmail(email) && this.validation.verificarNome(nome) && this.validation.verificarFoto(foto) && this.validation.verificarPassword(senha)) {
                Responsaveis responsaveisAdicionar = new Responsaveis(cpf, nome, dtNascimento, foto, senha, email);
                int resultado = 0;
                resultado = this.crudResponsavel.adicionarResponsavel(responsaveisAdicionar);
                if (resultado > 0) {
                    doGet(req, resp);
                } else {
                    req.setAttribute("erro", "Não foi possivel inserir um responsável.");
                }
            }
            req.getRequestDispatcher("/AreaRestrita/Responsaveis/areaRestritaResponsavelInserir.jsp").forward(req, resp);
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cpf = req.getParameter("cpfAtualizar");
        String nome = req.getParameter("nome");
        String dtNascimento = req.getParameter("dtNascimento");
        String foto = req.getParameter("foto");
        String senha = req.getParameter("senha");
        String email = req.getParameter("email");


        if (this.validation.verificarPassword(senha) && this.validation.verificarFoto(foto) && this.validation.verificarEmail(email) && this.validation.verificarNome(nome) && this.validation.verificarData(dtNascimento) && this.validation.verificarCPF(cpf)) {
            Responsaveis responsaveisAtualizada = new Responsaveis(cpf, nome, dtNascimento, foto, senha, email);
            int retornoLinhas = this.crudResponsavel.atualizarResponsavel(responsaveisAtualizada);
            if (retornoLinhas > 0) {
                doGet(req, resp);
            } else if (retornoLinhas == 0){
                req.setAttribute("erro", "Não foi possivel encontrar um responsável com esse CPF.");
            } else{
                req.setAttribute("erro", "Não foi possivel atualizar um responável.");
            }
        }else{
            req.setAttribute("erro", "Informações Incorretas.");
        }
        req.getRequestDispatcher("/AreaRestrita/Responsaveis/areaRestritaResponsavelAtualizar.jsp").forward(req, resp);
    }



    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cpf = req.getParameter("cpfDeletar");
        int resultado;

        // Verifica se é um CPF válido
        if (this.validation.verificarCPF(cpf)) {
            resultado = this.crudResponsavel.deletarResponsavel(cpf);
            if (resultado > 0) {
                doGet(req, resp);
            } else if (resultado == 0) {
                req.setAttribute("erro", "Não foi encontrado um responsável com esse CPF.");
            } else {
                req.setAttribute("erro", "Não foi possivel deletar um responsável, tente novamente mais tarde.");
            }
        }else{
            req.setAttribute("erro", "O CPF é inválido.");
        }
        req.getRequestDispatcher("/AreaRestrita/Responsaveis/areaRestritaResponsavelDeletar.jsp").forward(req, resp);
    }
}

