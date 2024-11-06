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

    private final TelefoneDAO crudTelefone = new TelefoneDAO(new Conexao());
    private final ResponsavelDAO crudResponsavel = new ResponsavelDAO(new Conexao());
    private final TransportadorDAO crudTransportador = new TransportadorDAO(new Conexao());
    private final Regex validation = new Regex();

    // Lida com requisições GET para listar telefones ou buscar um específico pelo ID
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        // Se não houver ID, lista todos os telefones
        if (id == null || id.isEmpty()) {
            List<Telefone> telefones = crudTelefone.listarTelefones();
            if (telefones != null) {
                req.setAttribute("telefones", telefones);
                req.getRequestDispatcher("/AreaRestrita/Telefone/areaRestritaTelefone.jsp").forward(req, resp);
            } else {
                req.setAttribute("erro", "Não foi encontrado nenhum Telefone.");
            }
        } else {
            // Busca telefone específico pelo ID
            List<Telefone> telefones = new ArrayList<>();
            try {
                Telefone telefone = crudTelefone.buscarTelefone(Integer.parseInt(id));
                if (telefone != null) {
                    telefones.add(telefone);
                    req.setAttribute("telefones", telefones);
                    req.getRequestDispatcher("/AreaRestrita/Telefone/areaRestritaTelefone.jsp").forward(req, resp);
                } else {
                    req.setAttribute("erro", "Não tem nenhum telefone com esse ID");
                }
            } catch (NumberFormatException nfe) {
                req.setAttribute("erro", "O ID deve ser um número");
            }
        }
        req.getRequestDispatcher("/AreaRestrita/Telefone/areaRestritaTelefone.jsp").forward(req, resp);
    }

    // Lida com requisições POST para inserir ou executar métodos PUT/DELETE conforme "method"
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        // Se "method" for delete ou put, chama os métodos respectivos
        if (method != null) {
            if ("delete".equals(method)) {
                doDelete(req, resp);
            } else if ("put".equals(method)) {
                doPut(req, resp);
            }
        } else {
            // Inserção de um novo telefone
            String numero = req.getParameter("telefone");
            String tipo = req.getParameter("tipo");
            String cnhOuCpf = req.getParameter("cnhOuCpf");

            // Valida telefone e CPF ou CNH
            if (validation.verificarTelefone(numero) && (validation.verificarCPF(cnhOuCpf) || validation.verificarCNH(cnhOuCpf))) {
                // Verifica se existe um responsável ou transportador com o CPF ou CNH
                if (crudResponsavel.buscarResponsavelPorCpf(cnhOuCpf) != null || crudTransportador.buscarTransportadorPorCnh(cnhOuCpf) != null) {
                    Telefone telefoneAdicionado = tipo.equals("responsavel") ? new Telefone(0, numero, cnhOuCpf, null) : new Telefone(0, numero, null, cnhOuCpf);
                    int retornoLinhas = crudTelefone.adicionarTelefone(telefoneAdicionado);
                    if (retornoLinhas > 0) {
                        doGet(req, resp); // Redireciona para listagem após inserção
                    } else {
                        req.setAttribute("erro", "Não foi possível adicionar um telefone.");
                    }
                } else {
                    req.setAttribute("erro", "Não existe um usuário com essa CNH/CPF.");
                }
            } else {
                req.setAttribute("erro", "Telefone e/ou CNH/CPF estão incorretos.");
            }
            req.getRequestDispatcher("/AreaRestrita/Telefone/areaRestritaTelefoneAtualizar.jsp").forward(req, resp);
        }
    }

    // Lida com requisições PUT para atualizar dados de um telefone
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("idAtualizar");
        String numero = req.getParameter("telefone");
        String idUsuario = req.getParameter("cnhOuCpf");
        String tipo = req.getParameter("tipo");

        try {
            // Valida telefone e CPF ou CNH do usuário
            if (validation.verificarTelefone(numero) && (validation.verificarCNH(idUsuario) || validation.verificarCPF(idUsuario))) {
                // Verifica se o usuário existe
                if (crudTransportador.buscarTransportadorPorCnh(idUsuario) != null || crudResponsavel.buscarResponsavelPorCpf(idUsuario) != null) {
                    Telefone telefoneAtualizado = tipo.equals("responsavel") ? new Telefone(Integer.parseInt(id), numero, idUsuario, null) : new Telefone(Integer.parseInt(id), numero, null, idUsuario);
                    int retornoLinhas = crudTelefone.atualizarTelefone(telefoneAtualizado);
                    if (retornoLinhas > 0) {
                        doGet(req, resp);
                    } else if (retornoLinhas == 0) {
                        req.setAttribute("erro", "Não existe um telefone com esse ID.");
                    } else {
                        req.setAttribute("erro", "Não foi possível atualizar um telefone.");
                    }
                } else {
                    req.setAttribute("erro", "Não existe um usuário com essa CNH/CPF.");
                }
            } else {
                req.setAttribute("erro", "Telefone e/ou CNH/CPF estão incorretos.");
            }
        } catch (NumberFormatException nfe) {
            req.setAttribute("erro", "O ID deve ser apenas números.");
        }
        req.getRequestDispatcher("/AreaRestrita/Telefone/areaRestritaTelefoneInserir.jsp").forward(req, resp);
    }

    // Lida com requisições DELETE para remover um telefone pelo ID
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("idDeletar");
        int resultado;

        try {
            // Verifica se o ID é válido (número maior que 0)
            if (Integer.parseInt(id) > 0) {
                resultado = crudTelefone.deletarTelefone(Integer.parseInt(id));
                if (resultado > 0) {
                    doGet(req, resp);
                } else if (resultado == 0) {
                    req.setAttribute("erro", "Não foi encontrado um Telefone com esse ID.");
                } else {
                    req.setAttribute("erro", "Não foi possível deletar um Telefone, tente novamente mais tarde.");
                }
            } else {
                req.setAttribute("erro", "O ID deve ser maior que 0.");
            }
        } catch (NumberFormatException nfe) {
            req.setAttribute("erro", "O ID deve conter apenas números");
        }

        req.getRequestDispatcher("/AreaRestrita/Telefone/areaRestritaTelefoneDeletar.jsp").forward(req, resp);
    }
}
