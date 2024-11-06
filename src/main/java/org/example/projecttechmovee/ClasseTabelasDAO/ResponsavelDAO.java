package org.example.projecttechmovee.ClasseTabelasDAO;

import org.example.projecttechmovee.ClasseTabelas.Responsaveis;
import org.example.projecttechmovee.DbConexao.Conexao;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ResponsavelDAO {
    private final Conexao conexao;

    // Construtor para inicializar a conexão
    public ResponsavelDAO(Conexao connection) {
        this.conexao = connection;
    }

    // Método para adicionar um novo responsável ao banco de dados
    public int adicionarResponsavel(Responsaveis responsavel) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato de data
        if(conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Prepara a consulta SQL para inserir um novo responsável
                    PreparedStatement pstmt = connection.prepareStatement(
                            "INSERT INTO Responsaveis (cpf_resp, nome, dt_nascimento, foto, senha, email) VALUES (?, ?, ?, ?, ?, ?)"
                    );
                    pstmt.setString(1, responsavel.getCpf());
                    pstmt.setString(2, responsavel.getNome());
                    // Converte a data de nascimento para o formato correto
                    pstmt.setDate(3, Date.valueOf(LocalDate.parse(responsavel.getDtNascimento(), formatoData)));
                    pstmt.setString(4, responsavel.getFoto());
                    pstmt.setString(5, responsavel.getSenha());
                    pstmt.setString(6, responsavel.getEmail());

                    return pstmt.executeUpdate(); // Executa a inserção e retorna o resultado
                }
            } catch (SQLException s) {
                s.printStackTrace(); // Exibe o erro no terminal em caso de exceção
            } finally {
                conexao.desconectar(); // Garante que a conexão seja fechada
            }
        }
        return -1; // Retorna -1 caso a inserção não tenha sido bem-sucedida
    }

    // Método para atualizar os dados de um responsável existente
    public int atualizarResponsavel(Responsaveis responsavel) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato de data
        if(conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Prepara a consulta SQL para atualizar os dados do responsável
                    PreparedStatement pstmt = connection.prepareStatement(
                            "UPDATE Responsaveis SET nome = ?, dt_nascimento = ?, foto = ?, senha = ?, email = ? WHERE cpf_resp = ?"
                    );
                    pstmt.setString(1, responsavel.getNome());
                    pstmt.setDate(2, Date.valueOf(LocalDate.parse(responsavel.getDtNascimento(), formatoData))); // Converte a data
                    pstmt.setString(3, responsavel.getFoto());
                    pstmt.setString(4, responsavel.getSenha());
                    pstmt.setString(5, responsavel.getEmail());
                    pstmt.setString(6, responsavel.getCpf());

                    return pstmt.executeUpdate(); // Executa a atualização e retorna o número de linhas afetadas
                }
            } catch (SQLException se) {
                se.printStackTrace(); // Exibe o erro no terminal em caso de exceção
            } finally {
                conexao.desconectar(); // Garante que a conexão seja fechada
            }
        }
        return -1; // Retorna -1 caso a atualização não tenha sido bem-sucedida
    }

    // Método para buscar um responsável pelo CPF
    public Responsaveis buscarResponsavelPorCpf(String cpf) {
        if(conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Prepara a consulta SQL para buscar um responsável pelo CPF
                    PreparedStatement pstmt = connection.prepareStatement(
                            "SELECT * FROM Responsaveis WHERE cpf_resp = ?"
                    );
                    pstmt.setString(1, cpf);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        // Retorna o responsável caso seja encontrado no banco de dados
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
                se.printStackTrace(); // Exibe o erro no terminal em caso de exceção
            } finally {
                conexao.desconectar(); // Garante que a conexão seja fechada
            }
        }
        return null; // Retorna null caso o responsável não seja encontrado
    }

    // Método para listar todos os responsáveis no banco de dados
    public List<Responsaveis> listarResponsaveis() {
        List<Responsaveis> responsaveisList = new ArrayList<>(); // Lista para armazenar os responsáveis
        if(conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                // Prepara a consulta SQL para selecionar todos os responsáveis
                PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Responsaveis");
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    // Adiciona cada responsável encontrado à lista
                    responsaveisList.add(new Responsaveis(
                            rs.getString("cpf_resp"),
                            rs.getString("nome"),
                            rs.getString("dt_nascimento"),
                            rs.getString("foto"),
                            rs.getString("senha"),
                            rs.getString("email")
                    ));
                }
                return responsaveisList; // Retorna a lista após o loop
            } catch (SQLException se) {
                se.printStackTrace(); // Exibe o erro no terminal em caso de exceção
            } finally {
                conexao.desconectar(); // Garante que a conexão seja fechada
            }
        }
        return responsaveisList; // Retorna a lista de responsáveis (vazia se nenhum foi encontrado)
    }

    // Método para remover um responsável pelo CPF
    public int deletarResponsavel(String cpf) {
        if(conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Prepara a consulta SQL para deletar um responsável pelo CPF
                    PreparedStatement pstmt = connection.prepareStatement(
                            "DELETE FROM Responsaveis WHERE cpf_resp = ?"
                    );
                    pstmt.setString(1, cpf);
                    return pstmt.executeUpdate(); // Executa a remoção e retorna o número de linhas alteradas
                }
            } catch (SQLException se) {
                se.printStackTrace(); // Exibe o erro no terminal em caso de exceção
            } finally {
                conexao.desconectar(); // Garante que a conexão seja fechada
            }
        }
        return -1; // Retorna -1 caso a remoção não tenha sido bem-sucedida
    }
}