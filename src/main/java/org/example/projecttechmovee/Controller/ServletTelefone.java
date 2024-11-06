package org.example.projecttechmovee.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projecttechmovee.ClasseTabelas.Telefone;
import org.example.projecttechmovee.ClasseTabelasDAO.ResponsavelDAO;
import org.example.projecttechmovee.ClasseTabelasDAO.TelefoneDAO;
import org.example.projecttechmovee.ClasseTabelasDAO.TransportadorDAO;
import org.example.projecttechmovee.DbConexao.Conexao;
import org.example.projecttechmovee.Regex.Regex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Telefone", value = "/Telefone")
public class ServletTelefone extends HttpServlet {

    private TelefoneDAO crudTelefone = new TelefoneDAO(new Conexao());
    private ResponsavelDAO crudResponsavel = new ResponsavelDAO(new Conexao());
    private TransportadorDAO crudTransportador = new TransportadorDAO(new Conexao());
    private Regex validation = new Regex();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            List<Telefone> telefones = this.crudTelefone.listarTelefones();
            if (telefones != null) {
                req.setAttribute("telefones", telefones);
                req.getRequestDispatcher("/AreaRestrita/Telefone/areaRestritaTelefone.jsp").forward(req, resp);
            } else {
                req.setAttribute("erro", "Não foi encontrado nenhum Telefone.");
            }
        } else {
            List<Telefone> telefones = new ArrayList<>();
            try {
                if (this.crudTelefone.buscarTelefone(Integer.parseInt(id)) != null) {
                    telefones.add(this.crudTelefone.buscarTelefone(Integer.parseInt(id)));
                    req.setAttribute("telefones", telefones);
                    req.getRequestDispatcher("/AreaRestrita/Telefone/areaRestritaTelefone.jsp").forward(req, resp);
                } else {
                    req.setAttribute("erro", "Não tem nenhum telefone com esse ID");
                }
            }catch (NumberFormatException nfe){
                req.setAttribute("erro", "O ID deve ser um número");
            }
        }
        req.getRequestDispatcher("/AreaRestrita/Telefone/areaRestritaTelefone.jsp").forward(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method != null) {
            if (method.equals("delete")) {
                doDelete(req, resp);
            } else if (method.equals("put")) {
                doPut(req, resp);
            }
        } else {

//      Pegando os valores de email e password enviado pelo o usuário no formulário da requisição.
            String numero = req.getParameter("telefone");
            String tipo = req.getParameter("tipo");
            String cnhOuCpf = req.getParameter("cnhOuCpf");

            if (this.validation.verificarTelefone(numero) && (this.validation.verificarCPF(cnhOuCpf) || this.validation.verificarCNH(cnhOuCpf))) {
                if (this.crudResponsavel.buscarResponsavelPorCpf(cnhOuCpf) != null || this.crudTransportador.buscarTransportadorPorCnh(cnhOuCpf) != null) {
                    Telefone telefoneAdicionado = tipo.equals("responsavel") ? new Telefone(0, numero, cnhOuCpf, null) : new Telefone(0, numero, null, cnhOuCpf);
                    int retornoLinhas = this.crudTelefone.adicionarTelefone(telefoneAdicionado);
                    if (retornoLinhas > 0) {
                        doGet(req, resp);
                    } else {
                        req.setAttribute("erro", "Não foi possivel adicionar um telefone.");
                    }
                }else{
                    req.setAttribute("erro","Não existe um usuário com essa CNH/CPF.");
                }
            }else{
                req.setAttribute("erro", "Telefone e/ou CNH/CPF estão incorretos.");
            }
            req.getRequestDispatcher("/AreaRestrita/Telefone/areaRestritaTelefoneAtualizar.jsp").forward(req, resp);
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("idAtualizar");
        String numero = req.getParameter("telefone");
        String idUsuario = req.getParameter("cnhOuCpf");
        String tipo = req.getParameter("tipo");
        try {
            if (this.validation.verificarTelefone(numero) && (this.validation.verificarCNH(idUsuario) || this.validation.verificarCPF(idUsuario))) {
                if (this.crudTransportador.buscarTransportadorPorCnh(idUsuario) != null || this.crudResponsavel.buscarResponsavelPorCpf(idUsuario) != null) {
                    Telefone telefoneAtualizado = tipo.equals("responsavel") ? new Telefone(Integer.parseInt(id), numero, idUsuario, null) : new Telefone(Integer.parseInt(id), numero, null, idUsuario);
                    int retornoLinhas = this.crudTelefone.atualizarTelefone(telefoneAtualizado);
                    if (retornoLinhas > 0) {
                        doGet(req, resp);
                    } else if (retornoLinhas == 0) {
                        req.setAttribute("erro", "Não existe um telefone com esse ID.");
                    } else {
                        req.setAttribute("erro", "Não foi possivel atualizar um telefone.");
                    }
                }else{
                    req.setAttribute("erro","Não existe um usuário com essa CNH/CPF.");
                }
            }else{
                req.setAttribute("erro","Telefone e/ou CNH/CPF estão incorretos.");
            }
        }catch (NumberFormatException nfe){
            req.setAttribute("erro", "O ID deve ser apenas números.");
        }
        req.getRequestDispatcher("/AreaRestrita/Telefone/areaRestritaTelefoneInserir.jsp").forward(req, resp);
    }


    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("idDeletar");
        int resultado;
        try {
            // Verifica se é um número válido (ID)
            if (Integer.parseInt(id) > 0) {
                resultado = this.crudTelefone.deletarTelefone(Integer.parseInt(id));
                if (resultado>0){
                    doGet(req, resp);
                }else if (resultado == 0){
                    req.setAttribute("erro", "Não foi encontrado um Telefone com esse ID.");
                }else{
                    req.setAttribute("erro", "Não foi possivel deletar um Telefone, tente novamente mais tarde.");
                }
            }else{
                req.setAttribute("erro", "O ID deve ser maior que 0.");
            }
        } catch (NumberFormatException nfe) {
            req.setAttribute("erro", "O ID deve conter apenas números");
        }
        req.getRequestDispatcher("/AreaRestrita/Telefone/areaRestritaTelefoneDeletar.jsp").forward(req, resp);
    }
}
