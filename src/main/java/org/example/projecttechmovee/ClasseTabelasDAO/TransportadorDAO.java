package org.example.projecttechmovee.ClasseTabelasDAO;

import org.example.projecttechmovee.ClasseTabelas.Transportador;
import org.example.projecttechmovee.DbConexao.Conexao;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class TransportadorDAO {
    // Atributo que armazena a conexão com o banco de dados
    private Conexao conexao;

    // Construtor que inicializa a conexão
    public TransportadorDAO(Conexao connection) {
        this.conexao = connection;
    }

    // Método para adicionar um transportador ao banco de dados
    public int adicionarTransportador(Transportador transportador) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida

            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Comando SQL para inserir um transportador na tabela 'Transportadores'
                    PreparedStatement pstmt = connection.prepareStatement(
                            "INSERT INTO Transportadores (cnh, nome, cep, email, senha, dt_nascimento, foto) VALUES (?, ?, ?, ?, ?, ?, ?)"
                    );
                    pstmt.setString(1, transportador.getCnh());
                    pstmt.setString(2, transportador.getNome());
                    pstmt.setString(3, transportador.getCep());
                    pstmt.setString(4, transportador.getEmail());
                    pstmt.setString(5, transportador.getSenha());
                    pstmt.setDate(6, Date.valueOf(LocalDate.parse(transportador.getDtNascimento(), formatoData)));
                    pstmt.setString(7, transportador.getFoto());

                    return pstmt.executeUpdate(); // Retorna o número de linhas afetadas
                }
            } catch (SQLException se) {
                se.printStackTrace();  // Exibe a pilha de erros em caso de exceção
            } finally {
                conexao.desconectar(); // Garantindo que a conexão seja fechada
            }
        }
        return -1; // Indica que não houve adição
    }

    // Método para atualizar um transportador no banco de dados
    public int atualizarTransportador(Transportador transportador) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida

            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Comando SQL para atualizar os dados de um transportador
                    PreparedStatement pstmt = connection.prepareStatement(
                            "UPDATE Transportadores SET nome = ?, cep = ?, email = ?, senha = ?, dt_nascimento = ?, foto = ? WHERE cnh = ?"
                    );
                    pstmt.setString(1, transportador.getNome());
                    pstmt.setString(2, transportador.getCep());
                    pstmt.setString(3, transportador.getEmail());
                    pstmt.setString(4, transportador.getSenha());
                    pstmt.setDate(5, Date.valueOf(LocalDate.parse(transportador.getDtNascimento(), formatoData)));
                    pstmt.setString(6, transportador.getFoto());
                    pstmt.setString(7, transportador.getCnh());

                    return pstmt.executeUpdate(); // Retorna o número de linhas afetadas
                }
            } catch (SQLException se) {
                se.printStackTrace();  // Exibe a pilha de erros em caso de exceção
            } finally {
                conexao.desconectar(); // Garantindo que a conexão seja fechada
            }
        }
        return -1; // Indica que não houve atualização
    }

    // Método para buscar um transportador por CNH
    public Transportador buscarTransportadorPorCnh(String cnh) {
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida

            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Comando SQL para buscar um transportador pela CNH
                    PreparedStatement pstmt = connection.prepareStatement(
                            "SELECT * FROM Transportadores WHERE cnh = ?"
                    );
                    pstmt.setString(1, cnh);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        // Retorna um objeto Transportador com os dados obtidos
                        return new Transportador(
                                rs.getString("cnh"),
                                rs.getString("nome"),
                                rs.getString("cep"),
                                rs.getString("email"),
                                rs.getString("senha"),
                                rs.getString("dt_nascimento"),
                                rs.getString("foto")
                        );
                    }
                }
            } catch (SQLException se) {
                se.printStackTrace();  // Exibe a pilha de erros em caso de exceção
            } finally {
                conexao.desconectar(); // Garantindo que a conexão seja fechada
            }
        }
        return null;  // Retorna null se o transportador não for encontrado
    }

    // Método para ler todos os transportadores
    public List<Transportador> listarTransportadores() {
        List<Transportador> transportadores = new ArrayList<>();
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida
            Connection connection = conexao.getConexao(); // Obtenha a conexão aqui
            try {
                if (connection != null) {
                    // Comando SQL para selecionar todos os transportadores
                    PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Transportadores");
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        // Cria um objeto Transportador e adiciona à lista
                        Transportador transportador = new Transportador(
                                rs.getString("cnh"),
                                rs.getString("nome"),
                                rs.getString("cep"),
                                rs.getString("email"),
                                rs.getString("senha"),
                                rs.getString("dt_nascimento"),
                                rs.getString("foto")
                        );
                        transportadores.add(transportador);
                    }
                }
            } catch (SQLException se) {
                se.printStackTrace();  // Exibe a pilha de erros em caso de exceção
            } finally {
                conexao.desconectar(); // Garante que a conexão seja fechada
            }
        }
        return transportadores; // Retorna a lista de transportadores
    }

    // Método para remover um transportador
    public int deletarTransportador(String cnh) {
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida

            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Comando SQL para deletar um transportador pela CNH
                    PreparedStatement pstmt = connection.prepareStatement(
                            "DELETE FROM Transportadores WHERE cnh = ?"
                    );
                    pstmt.setString(1, cnh);
                    return pstmt.executeUpdate(); // Retorna o número de linhas afetadas
                }
            } catch (SQLException se) {
                se.printStackTrace();  // Exibe a pilha de erros em caso de exceção
            } finally {
                conexao.desconectar(); // Garantindo que a conexão seja fechada
            }
        }
        return -1; // Indica que não houve remoção
    }
}


