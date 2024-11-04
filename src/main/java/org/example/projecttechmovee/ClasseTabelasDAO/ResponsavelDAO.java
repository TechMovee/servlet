package org.example.projecttechmovee.ClasseTabelasDAO;

import org.example.projecttechmovee.ClasseTabelas.Responsaveis;
import org.example.projecttechmovee.DbConexao.Conexao;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ResponsavelDAO {
    private Conexao conexao;

    //    Construtor
    public ResponsavelDAO(Conexao connection) {
        this.conexao = connection;
    }

    // Adicionar um responsável
    public int adicionarResponsavel(Responsaveis responsavel)  {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(conexao.conectar()) {

            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement(
                            "INSERT INTO Responsaveis (cpf_resp, nome, dt_nascimento, foto, senha, email) VALUES (?, ?, ?, ?, ?, ?)"
                    );
                    pstmt.setString(1, responsavel.getCpf());
                    pstmt.setString(2, responsavel.getNome());
                    pstmt.setDate(6, Date.valueOf(LocalDate.parse(responsavel.getDtNascimento(), formatoData)));
                    pstmt.setString(4, responsavel.getFoto());
                    pstmt.setString(5, responsavel.getSenha());
                    pstmt.setString(6, responsavel.getEmail());

                    return pstmt.executeUpdate();
                }
            } catch (SQLException s) {
                s.printStackTrace();
            } finally {
                conexao.desconectar();
            }
        }
        return -1;
    }

    // Atualizar um responsável
    public int atualizarResponsavel(Responsaveis responsavel)  {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement(
                            "UPDATE Responsaveis SET nome = ?, dt_nascimento = ?, foto = ?, senha = ?, email = ? WHERE cpf_resp = ?"
                    );
                    pstmt.setString(1, responsavel.getNome());
                    pstmt.setDate(6, Date.valueOf(LocalDate.parse(responsavel.getDtNascimento(), formatoData)));
                    pstmt.setString(3, responsavel.getFoto());
                    pstmt.setString(4, responsavel.getSenha());
                    pstmt.setString(5, responsavel.getEmail());
                    pstmt.setString(6, responsavel.getCpf());

                    return pstmt.executeUpdate(); // Retorna o número de linhas afetadas
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar(); // Garantindo que a conexão seja fechada
            }
        }
        return -1;
    }

    // Buscar um responsável por CPF
    public Responsaveis buscarResponsavelPorCpf(String cpf)  {
        if(conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement(
                            "SELECT * FROM Responsaveis WHERE cpf_resp = ?"
                    );
                    pstmt.setString(1, cpf);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                         return new Responsaveis(
                                rs.getString("cpf_resp"),
                                rs.getString("nome"),
                                rs.getString("dt_nascimento"),
                                rs.getString("foto"),
                                rs.getString("senha"),
                                rs.getString("email")
                        );
                    }
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar(); // Garantindo que a conexão seja fechada
            }
        }
        return null; // Retorna o responsável encontrado ou null se não encontrado

    }

    // Listar todos os responsáveis
    public List<Responsaveis> listarResponsaveis() {
        List<Responsaveis> responsaveisList = new ArrayList<>();
        if(conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Responsaveis");
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    responsaveisList.add(new Responsaveis(
                            rs.getString("cpf_resp"),
                            rs.getString("nome"),
                            rs.getString("dt_nascimento"),
                            rs.getString("foto"),
                            rs.getString("senha"),
                            rs.getString("email")
                        ));
                }
                return responsaveisList; // Retorna após o loop
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar();
            }
        }
        return responsaveisList;
    }



    // Remover um responsável
    public int deletarResponsavel(String cpf)  {
        if(conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement(
                            "DELETE FROM Responsaveis WHERE cpf_resp = ?"
                    );
                    pstmt.setString(1, cpf);
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
