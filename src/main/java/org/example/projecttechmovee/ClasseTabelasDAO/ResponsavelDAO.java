package org.example.projecttechmovee.ClasseTabelasDAO;

import org.example.projecttechmovee.ClasseTabelas.Responsaveis;
import org.example.projecttechmovee.DbConexao.Conexao;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ResponsavelDAO {
    // Atributo que armazena a conexão com o banco de dados
    private Conexao conexao;

    // Construtor que inicializa a conexão
    public ResponsavelDAO(Conexao connection) {
        this.conexao = connection;
    }

    // Método para adicionar um responsável no banco de dados
    public int adicionarResponsavel(Responsaveis responsavel) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Formato de data
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Comando SQL para inserir um responsável na tabela 'Responsaveis'
                    PreparedStatement pstmt = connection.prepareStatement(
                            "INSERT INTO Responsaveis (cpf_resp, nome, dt_nascimento, foto, senha, email) VALUES (?, ?, ?, ?, ?, ?)"
                    );
                    pstmt.setString(1, responsavel.getCpf());
                    pstmt.setString(2, responsavel.getNome());
                    pstmt.setDate(3, Date.valueOf(LocalDate.parse(responsavel.getDtNascimento(), formatoData)));
                    pstmt.setString(4, responsavel.getFoto());
                    pstmt.setString(5, responsavel.getSenha());
                    pstmt.setString(6, responsavel.getEmail());

                    return pstmt.executeUpdate();  // Executa a inserção e retorna o número de linhas afetadas
                }
            } catch (SQLException s) {
                s.printStackTrace();  // Exibe a pilha de erros em caso de exceção
            } finally {
                conexao.desconectar();  // Fecha a conexão
            }
        }
        return -1;  // Retorna -1 se a operação falhar
    }

    // Método para atualizar os dados de um responsável no banco de dados
    public int atualizarResponsavel(Responsaveis responsavel) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Comando SQL para atualizar os dados de um responsável
                    PreparedStatement pstmt = connection.prepareStatement(
                            "UPDATE Responsaveis SET nome = ?, dt_nascimento = ?, foto = ?, senha = ?, email = ? WHERE cpf_resp = ?"
                    );
                    pstmt.setString(1, responsavel.getNome());
                    pstmt.setDate(2, Date.valueOf(LocalDate.parse(responsavel.getDtNascimento(), formatoData)));
                    pstmt.setString(3, responsavel.getFoto());
                    pstmt.setString(4, responsavel.getSenha());
                    pstmt.setString(5, responsavel.getEmail());
                    pstmt.setString(6, responsavel.getCpf());

                    return pstmt.executeUpdate();  // Executa a atualização e retorna o número de linhas afetadas
                }
            } catch (SQLException se) {
                se.printStackTrace();  // Exibe a pilha de erros em caso de exceção
            } finally {
                conexao.desconectar();  // Fecha a conexão
            }
        }
        return -1;  // Retorna -1 se a operação falhar
    }

    // Método para buscar um responsável pelo CPF
    public Responsaveis buscarResponsavelPorCpf(String cpf) {
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Comando SQL para buscar um responsável pelo CPF
                    PreparedStatement pstmt = connection.prepareStatement(
                            "SELECT * FROM Responsaveis WHERE cpf_resp = ?"
                    );
                    pstmt.setString(1, cpf);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        // Cria e retorna um objeto 'Responsaveis' com os dados obtidos do ResultSet
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
                se.printStackTrace();  // Exibe a pilha de erros em caso de exceção
            } finally {
                conexao.desconectar();  // Fecha a conexão
            }
        }
        return null;  // Retorna null se o responsável não for encontrado
    }

    // Método para listar todos os responsáveis no banco de dados
    public List<Responsaveis> listarResponsaveis() {
        List<Responsaveis> responsaveisList = new ArrayList<>();
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida
            Connection connection = conexao.getConexao();
            try {
                // Comando SQL para selecionar todos os responsáveis
                PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Responsaveis");
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    // Adiciona cada responsável obtido à lista 'responsaveisList'
                    responsaveisList.add(new Responsaveis(
                            rs.getString("cpf_resp"),
                            rs.getString("nome"),
                            rs.getString("dt_nascimento"),
                            rs.getString("foto"),
                            rs.getString("senha"),
                            rs.getString("email")
                    ));
                }
                rs.close();  // Fecha o ResultSet
                pstmt.close();  // Fecha o PreparedStatement
            } catch (SQLException se) {
                se.printStackTrace();  // Exibe a pilha de erros em caso de exceção
            } finally {
                conexao.desconectar();  // Fecha a conexão
            }
        }
        return responsaveisList;  // Retorna a lista de responsáveis
    }

    // Método para remover um responsável pelo CPF
    public int deletarResponsavel(String cpf) {
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Comando SQL para deletar um responsável pelo CPF
                    PreparedStatement pstmt = connection.prepareStatement(
                            "DELETE FROM Responsaveis WHERE cpf_resp = ?"
                    );
                    pstmt.setString(1, cpf);

                    return pstmt.executeUpdate();  // Executa a remoção e retorna o número de linhas afetadas
                }
            } catch (SQLException se) {
                se.printStackTrace();  // Exibe a pilha de erros em caso de exceção
            } finally {
                conexao.desconectar();  // Fecha a conexão
            }
        }
        return -1;  // Retorna -1 se a operação falhar
    }
}
