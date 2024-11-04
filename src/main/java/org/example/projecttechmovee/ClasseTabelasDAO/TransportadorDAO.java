package org.example.projecttechmovee.ClasseTabelasDAO;

import org.example.projecttechmovee.ClasseTabelas.Transportador;
import org.example.projecttechmovee.DbConexao.Conexao;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class TransportadorDAO {
    private Conexao conexao;

    //    CONSTRUTOR
    public TransportadorDAO(Conexao connection) {
        this.conexao = connection;
    }

    //    ADICIONAR TRANSPORTADOR
    public int adicionarTransportador(Transportador transportador) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (conexao.conectar()) {

            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
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
                se.printStackTrace();
            } finally {
                conexao.desconectar(); // Garantindo que a conexão seja fechada
            }
        }
        return -1; // Indica que não houve adição
    }

    // Atualizar um transportador
    public int atualizarTransportador(Transportador transportador) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (conexao.conectar()) {

            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement(
                            "UPDATE Transportadores SET nome = ?, cep = ?, email = ?, senha = ?, dt_nascimento = ?, foto = ? WHERE cnh = ?"
                    );
                    pstmt.setString(1, transportador.getNome());
                    pstmt.setString(2, transportador.getCep());
                    pstmt.setString(3, transportador.getEmail());
                    pstmt.setString(4, transportador.getSenha());
                    pstmt.setDate(6, Date.valueOf(LocalDate.parse(transportador.getDtNascimento(), formatoData)));
                    pstmt.setString(6, transportador.getFoto());
                    pstmt.setString(7, transportador.getCnh());

                    return pstmt.executeUpdate(); // Retorna o número de linhas afetadas
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar(); // Garantindo que a conexão seja fechada
            }
        }
        return -1; // Indica que não houve atualização
    }

    // Buscar um transportador por CNH
    public Transportador buscarTransportadorPorCnh(String cnh) {
        if (conexao.conectar()) {

            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement(
                            "SELECT * FROM Transportadores WHERE cnh = ?"
                    );
                    pstmt.setString(1, cnh);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
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
                se.printStackTrace();
            } finally {
                conexao.desconectar(); // Garantindo que a conexão seja fechada
            }
        }
        return null;
    }

    //Ler todos os transportadores
    public List<Transportador> listarTransportadores() {
        List<Transportador> transportadores = new ArrayList<>();
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao(); // Obtenha a conexão aqui
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Transportadores");
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
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
                se.printStackTrace();
            } finally {
                conexao.desconectar(); // Garante que a conexão seja fechada
            }
        }
        return transportadores;
    }

    // Remover um transportador
    public int deletarTransportador(String cnh) {
        if (conexao.conectar()) {

            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement(
                            "DELETE FROM Transportadores WHERE cnh = ?"
                    );
                    pstmt.setString(1, cnh);
                    return pstmt.executeUpdate(); // Retorna o número de linhas afetadas
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar(); // Garantindo que a conexão seja fechada
            }
        }
        return -1; // Indica que não houve remoção
    }
}


