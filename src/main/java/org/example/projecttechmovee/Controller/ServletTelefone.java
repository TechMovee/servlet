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

    // Instâncias dos DAOs para interagir com os dados de telefone, responsável e transportador
    private TelefoneDAO crudTelefone = new TelefoneDAO(new Conexao());
    private ResponsavelDAO crudResponsavel = new ResponsavelDAO(new Conexao());
    private TransportadorDAO crudTransportador = new TransportadorDAO(new Conexao());
    // Instância da classe Regex para validações de dados
    private Regex validation = new Regex();

    // Método doGet para lidar com as requisições GET
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id"); // Obtém o ID do telefone da requisição
        if (id == null || id.isEmpty()) {
            // Se ID não for fornecido, lista todos os telefones
            List<Telefone> telefones = this.crudTelefone.listarTelefones();
            if (telefones != null) {
                req.setAttribute("telefones", telefones); // Adiciona a lista de telefones ao atributo da requisição
                req.getRequestDispatcher("/AreaRestrita/Telefone/areaRestritaTelefone.jsp").forward(req, resp); // Redireciona para a página
            } else {
                req.setAttribute("erro", "Não foi encontrado nenhum Telefone."); // Mensagem de erro se não encontrar
            }
        } else {
            // Se ID for fornecido, busca um telefone específico
            List<Telefone> telefones = new ArrayList<>();
            try {
                if (this.crudTelefone.buscarTelefone(Integer.parseInt(id)) != null) {
                    telefones.add(this.crudTelefone.buscarTelefone(Integer.parseInt(id))); // Adiciona o telefone encontrado à lista
                    req.setAttribute("telefones", telefones); // Adiciona o telefone ao atributo da requisição
                    req.getRequestDispatcher("/AreaRestrita/Telefone/areaRestritaTelefone.jsp").forward(req, resp); // Redireciona para a página
                } else {
                    req.setAttribute("erro", "Não tem nenhum telefone com esse ID"); // Mensagem de erro se não encontrar
                }
            } catch (NumberFormatException nfe) {
                req.setAttribute("erro", "O ID deve ser um número"); // Mensagem de erro se o ID não for um número
            }
        }
        req.getRequestDispatcher("/AreaRestrita/Telefone/areaRestritaTelefone.jsp").forward(req, resp); // Redireciona para a página (redundante)
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
            String numero = req.getParameter("telefone");
            String tipo = req.getParameter("tipo");
            String cnhOuCpf = req.getParameter("cnhOuCpf");

            // Valida o número do telefone e a CNH/CPF
            if (this.validation.verificarTelefone(numero) && (this.validation.verificarCPF(cnhOuCpf) || this.validation.verificarCNH(cnhOuCpf))) {
                // Verifica se existe um responsável ou transportador com a CNH/CPF fornecido
                if (this.crudResponsavel.buscarResponsavelPorCpf(cnhOuCpf) != null || this.crudTransportador.buscarTransportadorPorCnh(cnhOuCpf) != null) {
                    // Cria um objeto Telefone dependendo do tipo (responsável ou transportador)
                    Telefone telefoneAdicionado = tipo.equals("responsavel") ? new Telefone(0, numero, cnhOuCpf, null) : new Telefone(0, numero, null, cnhOuCpf);
                    int retornoLinhas = this.crudTelefone.adicionarTelefone(telefoneAdicionado); // Adiciona o telefone ao banco de dados
                    if (retornoLinhas > 0) {
                        doGet(req, resp); // Se adicionado com sucesso, chama doGet para atualizar a lista
                    } else {
                        req.setAttribute("erro", "Não foi possivel adicionar um telefone."); // Mensagem de erro se falhar
                    }
                } else {
                    req.setAttribute("erro", "Não existe um usuário com essa CNH/CPF."); // Mensagem de erro se não encontrar
                }
            } else {
                req.setAttribute("erro", "Telefone e/ou CNH/CPF estão incorretos."); // Mensagem de erro se a validação falhar
            }
            req.getRequestDispatcher("/AreaRestrita/Telefone/areaRestritaTelefoneAtualizar.jsp").forward(req, resp); // Redireciona para a página de atualização
        }
    }

    // Método doPut para atualizar um telefone
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("idAtualizar"); // Obtém o ID do telefone a ser atualizado
        String numero = req.getParameter("telefone");
        String idUsuario = req.getParameter("cnhOuCpf");
        String tipo = req.getParameter("tipo");

        try {
            // Valida o número do telefone e a CNH/CPF
            if (this.validation.verificarTelefone(numero) && (this.validation.verificarCNH(idUsuario) || this.validation.verificarCPF(idUsuario))) {
                // Verifica se existe um responsável ou transportador com a CNH/CPF fornecido
                if (this.crudTransportador.buscarTransportadorPorCnh(idUsuario) != null || this.crudResponsavel.buscarResponsavelPorCpf(idUsuario) != null) {
                    // Cria um objeto Telefone para atualização
                    Telefone telefoneAtualizado = tipo.equals("responsavel") ? new Telefone(Integer.parseInt(id), numero, idUsuario, null) : new Telefone(Integer.parseInt(id), numero, null, idUsuario);
                    int retornoLinhas = this.crudTelefone.atualizarTelefone(telefoneAtualizado); // Atualiza o telefone no banco de dados
                    if (retornoLinhas > 0) {
                        doGet(req, resp); // Se atualizado com sucesso, chama doGet para atualizar a lista
                    } else if (retornoLinhas == 0) {
                        req.setAttribute("erro", "Não existe um telefone com esse ID."); // Mensagem de erro se não encontrar
                    } else {
                        req.setAttribute("erro", "Não foi possivel atualizar um telefone."); // Mensagem de erro se falhar
                    }
                } else {
                    req.setAttribute("erro", "Não existe um usuário com essa CNH/CPF."); // Mensagem de erro se não encontrar
                }
            } else {
                req.setAttribute("erro", "Telefone e/ou CNH/CPF estão incorretos."); // Mensagem de erro se a validação falhar
            }
        } catch (NumberFormatException nfe) {
            req.setAttribute("erro", "O ID deve ser apenas números."); // Mensagem de erro se o ID não for um número
        }
        req.getRequestDispatcher("/AreaRestrita/Telefone/areaRestritaTelefoneInserir.jsp").forward(req, resp); // Redireciona para a página de inserção
    }

    // Método doDelete para deletar um telefone
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("idDeletar"); // Obtém o ID do telefone a ser deletado
        int resultado;
        try {
            // Verifica se é um número válido (ID)
            if (Integer.parseInt(id) > 0) {
                resultado = this.crudTelefone.deletarTelefone(Integer.parseInt(id)); // Tenta deletar o telefone
                if (resultado > 0) {
                    doGet(req, resp); // Se deletado com sucesso, chama doGet para atualizar a lista
                } else if (resultado == 0) {
                    req.setAttribute("erro", "Não foi encontrado um Telefone com esse ID."); // Mensagem de erro se não encontrar
                } else {
                    req.setAttribute("erro", "Não foi possivel deletar um Telefone, tente novamente mais tarde."); // Mensagem de erro se falhar
                }
            } else {
                req.setAttribute("erro", "O ID deve ser maior que 0."); // Mensagem de erro se o ID for inválido
            }
        } catch (NumberFormatException nfe) {
            req.setAttribute("erro", "O ID deve conter apenas números"); // Mensagem de erro se o ID não for um número
        }
        req.getRequestDispatcher("/AreaRestrita/Telefone/areaRestritaTelefoneDeletar.jsp").forward(req, resp); // Redireciona para a página de deletar
    }
}