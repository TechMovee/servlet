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
    // Inicializa o DAO de Transportador e a classe de validação
    private final TransportadorDAO crudTransportador = new TransportadorDAO(new Conexao());
    private final Regex validation = new Regex();

    // Método que trata requisições do tipo GET
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtém o parâmetro 'cnh' da requisição
        String cnh = req.getParameter("cnh");

        // Se 'cnh' não foi informado ou está vazio, lista todos os transportadores
        if (cnh == null || cnh.isEmpty()) {
            List<Transportador> transportadores = this.crudTransportador.listarTransportadores();
            if (transportadores != null) {
                // Adiciona a lista de transportadores à requisição e encaminha para a página JSP
                req.setAttribute("transp", transportadores);
                req.getRequestDispatcher("/AreaRestrita/Transportador/areaRestritaTransp.jsp").forward(req, resp);
            } else {
                // Se não houver transportadores, define uma mensagem de erro
                req.setAttribute("erro", "Não foi encontrado nenhum transportador.");
            }
        } else {
            // Caso contrário, busca o transportador pela CNH
            if (this.crudTransportador.buscarTransportadorPorCnh(cnh) != null) {
                List<Transportador> transportadores = new ArrayList<>();
                transportadores.add(this.crudTransportador.buscarTransportadorPorCnh(cnh));
                req.setAttribute("transp", transportadores);
                req.getRequestDispatcher("/AreaRestrita/Transportador/areaRestritaTransp.jsp").forward(req, resp);
            } else {
                // Se não encontrar, define uma mensagem de erro
                req.setAttribute("erro", "Não tem nenhum transportador com essa CNH.");
            }
        }
        // Encaminha novamente para a página JSP, mesmo que não tenha encontrado um transportador
        req.getRequestDispatcher("/AreaRestrita/Transportador/areaRestritaTransp.jsp").forward(req, resp);
    }

    // Método que trata requisições do tipo POST
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        // Verifica qual método deve ser chamado (delete ou put)
        if (method != null) {
            if (method.equals("delete")) {
                doDelete(req, resp);
            } else if (method.equals("put")) {
                doPut(req, resp);
            }
        } else {
            // Obtém os valores enviados no formulário
            String cnh = req.getParameter("cnhInserir");
            String nome = req.getParameter("nome");
            String cep = req.getParameter("cep");
            String senha = req.getParameter("senha");
            String dtNascimento = req.getParameter("dtNascimento");
            String foto = req.getParameter("foto");
            String email = req.getParameter("email");

            // Valida os dados recebidos
            if (this.validation.verificarData(dtNascimento) &&
                    this.validation.verificarCNH(cnh) &&
                    this.validation.verificarNome(nome) &&
                    this.validation.verificarEmail(email) &&
                    this.validation.verificarFoto(foto) &&
                    this.validation.verificarPassword(senha) &&
                    this.validation.verificarCEP(cep)) {

                // Cria um novo objeto Transportador com os dados validados
                Transportador transportador = new Transportador(cnh, nome, cep, email, senha, dtNascimento, foto);
                int resultado = this.crudTransportador.adicionarTransportador(transportador);

                // Verifica se a inserção foi bem-sucedida
                if (resultado > 0) {
                    doGet(req, resp); // Chama doGet para atualizar a lista de transportadores
                } else {
                    req.setAttribute("erro", "Não foi possível inserir um transportador.");
                }
            } else {
                req.setAttribute("erro", "Informações Incorretas.");
            }
        }
        // Encaminha para a página de inserção de transportadores
        req.getRequestDispatcher("/AreaRestrita/Transportador/areaRestritaTranspInserir.jsp").forward(req, resp);
    }

    // Método que trata requisições do tipo PUT
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtém os parâmetros necessários para atualizar um transportador
        String cnh = req.getParameter("cnhAtualizar");
        String nome = req.getParameter("nome");
        String cep = req.getParameter("cep");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String dtNascimento = req.getParameter("dtNascimento");
        String foto = req.getParameter("foto");

        // Valida os dados recebidos
        if (this.validation.verificarData(dtNascimento) &&
                this.validation.verificarCNH(cnh) &&
                this.validation.verificarNome(nome) &&
                this.validation.verificarEmail(email) &&
                this.validation.verificarFoto(foto) &&
                this.validation.verificarPassword(senha) &&
                this.validation.verificarCEP(cep)) {

            // Cria um novo objeto Transportador com os dados atualizados
            Transportador transportador = new Transportador(cnh, nome, cep, email, senha, dtNascimento, foto);
            int retornoLinhas = this.crudTransportador.atualizarTransportador(transportador);

            // Verifica o resultado da atualização
            if (retornoLinhas > 0) {
                doGet(req, resp); // Atualiza a lista de transportadores
            } else if (retornoLinhas == 0) {
                req.setAttribute("erro", "Não foi possível encontrar um Transportador com essa CNH.");
            } else {
                req.setAttribute("erro", "Não foi possível atualizar um Transportador.");
            }
        } else {
            req.setAttribute("erro", "Informações Incorretas.");
        }
        // Encaminha para a página de atualização de transportadores
        req.getRequestDispatcher("/AreaRestrita/Transportador/areaRestritaTranspAtualizar.jsp").forward(req, resp);
    }

    // Método que trata requisições do tipo DELETE
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtém a CNH do transportador a ser deletado
        String cnh = req.getParameter("cnhDeletar");
        int resultado;

        try {
            // Chama o método para deletar o transportador
            resultado= this.crudTransportador.deletarTransportador(cnh);
            // Verifica o resultado da operação de deleção
            if (resultado > 0) {
                doGet(req, resp); // Atualiza a lista de transportadores
            } else if (resultado == 0) {
                req.setAttribute("erro", "Não foi encontrado um Transportador com essa CNH.");
            } else {
                req.setAttribute("erro", "Não foi possível deletar um Transportador, tente novamente mais tarde.");
            }
        } catch (NumberFormatException nfe) {
            req.setAttribute("erro", "A CNH não deve conter letras.");
        }
        // Encaminha para a página de deleção de transportadores
        req.getRequestDispatcher("/AreaRestrita/Transportador/areaRestritaTranspDeletar.jsp").forward(req, resp);
    }
}
