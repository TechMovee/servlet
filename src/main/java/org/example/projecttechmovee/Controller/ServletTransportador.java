package org.example.projecttechmovee.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projecttechmovee.ClasseTabelas.Transportador;
import org.example.projecttechmovee.ClasseTabelasDAO.TransportadorDAO;
import org.example.projecttechmovee.DbConexao.Conexao;
import org.example.projecttechmovee.Regex.Regex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Transp", value = "/Transp")
public class ServletTransportador extends HttpServlet {
    private final TransportadorDAO crudTransportador = new TransportadorDAO(new Conexao());
    private final Regex validation = new Regex();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cnh = req.getParameter("cnh");
        if (cnh==null || cnh.isEmpty()) {
            List<Transportador> transportadores = this.crudTransportador.listarTransportadores();
            if (transportadores != null) {
                req.setAttribute("transp", transportadores);
                req.getRequestDispatcher("/AreaRestrita/Transportador/areaRestritaTransp.jsp").forward(req, resp);
            } else {
                req.setAttribute("erro", "Não foi encontrado nenhum transportador.");
            }
        }else{
            if (this.crudTransportador.buscarTransportadorPorCnh(cnh) != null) {
                List<Transportador> transportadores = new ArrayList<>();
                transportadores.add(this.crudTransportador.buscarTransportadorPorCnh(cnh));
                req.setAttribute("transp", transportadores);
                req.getRequestDispatcher("/AreaRestrita/Transportador/areaRestritaTransp.jsp").forward(req, resp);
            } else {
                req.setAttribute("erro", "Não tem nenhum transportador com essa CNH.");
            }
        }
        req.getRequestDispatcher("/AreaRestrita/Transportador/areaRestritaTransp.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");
        if (method != null) {
            if (method.equals("delete")) {
                doDelete(req, resp);
            } else if (method.equals("put")) {
                doPut(req, resp);
            }
        }else {

//      Pegando os valores de email e password enviado pelo o usuário no formulário da requisição.
            String cnh = req.getParameter("cnhInserir");
            String nome = req.getParameter("nome");
            String cep = req.getParameter("cep");
            String senha = req.getParameter("senha");
            String dtNascimento = req.getParameter("dtNascimento");
            String foto = req.getParameter("foto");
            String email = req.getParameter("email");
            if (this.validation.verificarData(dtNascimento) && this.validation.verificarCNH(cnh) && this.validation.verificarNome(nome) && this.validation.verificarEmail(email) && this.validation.verificarFoto(foto) && this.validation.verificarPassword(senha) && this.validation.verificarCEP(cep)) {
                Transportador transportador = new Transportador(cnh, nome, cep, email, senha, dtNascimento, foto);
                int resultado = this.crudTransportador.adicionarTransportador(transportador);
                if (resultado > 0) {
                    doGet(req, resp);
                } else {
                    req.setAttribute("erro", "Não foi possivel inserir um transportador.");
                }
            }else{
                req.setAttribute("erro", "Informações Incorretas.");
            }
        }
        req.getRequestDispatcher("/AreaRestrita/Transportador/areaRestritaTranspInserir.jsp").forward(req, resp);
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cnh = req.getParameter("cnhAtualizar");
        String nome = req.getParameter("nome");
        String cep = req.getParameter("cep");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String dtNascimento = req.getParameter("dtNascimento");
        String foto = req.getParameter("foto");

        if (this.validation.verificarData(dtNascimento) && this.validation.verificarCNH(cnh) && this.validation.verificarNome(nome) && this.validation.verificarEmail(email) && this.validation.verificarFoto(foto) && this.validation.verificarPassword(senha) && this.validation.verificarCEP(cep)) {
            Transportador transportador = new Transportador(cnh, nome, cep, email, senha, dtNascimento, foto);
            int retornoLinhas = this.crudTransportador.atualizarTransportador(transportador);
            if (retornoLinhas > 0) {
                doGet(req, resp);
            } else if( retornoLinhas==0){
                req.setAttribute("erro", "Não foi possivel encontrar um Transportador com essa CNH.");
            }else{
                req.setAttribute("erro", "Não foi possivel atualizar um Transportador.");
            }
        }else{
            req.setAttribute("erro", "Informações Incorretas.");
        }
        req.getRequestDispatcher("/AreaRestrita/Transportador/areaRestritaTranspAtualizar.jsp").forward(req, resp);
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cnh = req.getParameter("cnhDeletar");
        int resultado;

        try {
            // Verifica se é um número válido (ID)
            resultado = this.crudTransportador.deletarTransportador(cnh);
            if (resultado>0){
                doGet(req, resp);
            }else if (resultado == 0){
                req.setAttribute("erro", "Não foi encontrado um Transportador com essa CNH.");
            }else{
                req.setAttribute("erro", "Não foi possivel deletar um Transportador, tente novamente mais tarde.");
            }
        } catch (NumberFormatException nfe) {
            req.setAttribute("erro", "A CNH não deve conter letras.");
        }
        req.getRequestDispatcher("/AreaRestrita/Transportador/areaRestritaTranspDeletar.jsp").forward(req, resp);
    }
}
