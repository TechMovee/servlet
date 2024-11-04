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

    private final EscolaDAO crudEscola = new EscolaDAO(new Conexao());
    private final Regex validation = new Regex();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String id = req.getParameter("id");
        if (id==null || id.isEmpty()) {
            List<Escolas> escolas = this.crudEscola.listarEscolas();
            if (escolas != null) {
                req.setAttribute("escolas", escolas);
                req.getRequestDispatcher("/AreaRestrita/Escola/areaRestritaEscola.jsp").forward(req, resp);
            } else {
                req.setAttribute("erro", "Não foi encontrado nenhuma Escola.");
            }
        }else{
            List<Escolas> escolas = new ArrayList<>();
            try {
                if (this.crudEscola.buscarEscolaPorId(Integer.parseInt(id)) != null) {
                    escolas.add(this.crudEscola.buscarEscolaPorId(Integer.parseInt(id)));
                    req.setAttribute("escolas", escolas);
                    req.getRequestDispatcher("/AreaRestrita/Escola/areaRestritaEscola.jsp").forward(req, resp);
                } else {
                    req.setAttribute("erro", "Não tem nenhuma escola com esse ID.");}
            }
            catch (NumberFormatException nfe){
                req.setAttribute("erro", "O ID deve ser um número");
            }
        }
        req.getRequestDispatcher("/AreaRestrita/Escola/areaRestritaEscola.jsp").forward(req, resp);
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
            String nome = req.getParameter("nome");

            if (this.validation.verificarNome(nome)) {
                Escolas escolaAdicionada = new Escolas(nome);
                int resultado = this.crudEscola.adicionarEscola(escolaAdicionada);
                if (resultado > 0) {
                    doGet(req, resp);
                } else {
                    req.setAttribute("erro", "Não foi possivel inserir uma Escola.");
                }
            }else{
                req.setAttribute("erro","O nome é inválido.");
            }
        }
        req.getRequestDispatcher("/AreaRestrita/Escola/areaRestritaEscolaInserir.jsp").forward(req, resp);

    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("idAtualizar");
        String nome = req.getParameter("nome");

        try {
            if (this.validation.verificarNome(nome)) {
                Escolas escolaAtualizada = new Escolas(Integer.parseInt(id), nome);
                int retornoLinhas = this.crudEscola.atualizarEscola(escolaAtualizada);
                if (retornoLinhas > 0) {
                    doGet(req, resp);
                } else {
                    req.setAttribute("erro", "Não foi possivel atualizar a Escola.");
                }
            }else {
                req.setAttribute("erro", "Informações incorretas.");
            }
        }
        catch (NumberFormatException nfe){
            req.setAttribute("erro", "O ID deve ser um número.");
        }
        req.getRequestDispatcher("/AreaRestrita/Escola/areaRestritaEscolaAtualizar.jsp").forward(req, resp);
    }



    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("idDeletar");
        int resultado;

        try {
            // Verifica se é um número válido (ID)
            if (Integer.parseInt(id) > 0) {
                resultado = this.crudEscola.removerEscola(Integer.parseInt(id));
                if (resultado>0){
                    doGet(req, resp);
                }else if (resultado == 0){
                    req.setAttribute("erro", "Não foi encontrado uma escola com esse ID.");
                }else{
                    req.setAttribute("erro", "Não foi possivel deletar uma escola, tente novamente mais tarde.");
                }
            }else{
                req.setAttribute("erro", "O ID deve ser maior que 0.");
            }
        }
        catch (NumberFormatException nfe) {
            req.setAttribute("erro","O ID deve ser um número.");
        }
        req.getRequestDispatcher("/AreaRestrita/Escola/areaRestritaEscolaDeletar.jsp").forward(req, resp);
    }
}
