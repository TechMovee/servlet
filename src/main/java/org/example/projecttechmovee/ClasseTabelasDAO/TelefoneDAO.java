package org.example.projecttechmovee.ClasseTabelasDAO;

import org.example.projecttechmovee.ClasseTabelas.Telefone;
import org.example.projecttechmovee.DbConexao.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelefoneDAO {
    // Atributo que armazena a conexão com o banco de dados
    private Conexao conexao;

    // Construtor que inicializa a conexão
    public TelefoneDAO(Conexao conexao) {
        this.conexao = conexao;
    }

    // Método para adicionar um telefone no banco de dados
    public int adicionarTelefone(Telefone telefone) {
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Comando SQL para inserir um telefone na tabela Telefones
                    PreparedStatement pstmt = connection.prepareStatement(
                            "INSERT INTO Telefones (numero, id_transp, id_resp) VALUES (?, ?, ?)"
                    );
                    pstmt.setString(1, telefone.getNumero());
                    pstmt.setString(2, telefone.getId_transp());
                    pstmt.setString(3, telefone.getId_resp());
                    return pstmt.executeUpdate();  // Retorna o número de linhas afetadas
                }
            } catch (SQLException se) {
                se.printStackTrace();  // Exibe a pilha de erros em caso de exceção
            } finally {
                conexao.desconectar();  // Fecha a conexão
            }
        }
        return -1;  // Retorna -1 se a operação falhar
    }

    // Método para listar todos os telefones no banco de dados
    public List<Telefone> listarTelefones() {
        List<Telefone> telefones = new ArrayList<>();
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida
            Connection connection = conexao.getConexao();

            try {
                if (connection != null) {
                    // Comando SQL para selecionar todos os telefones
                    PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Telefones");
                    ResultSet rs = pstmt.executeQuery();

                    while (rs.next()) {
                        // Cria um objeto Telefone e adiciona à lista
                        Telefone telefone = new Telefone(
                                rs.getInt("id"),
                                rs.getString("numero"),
                                rs.getString("id_resp"),
                                rs.getString("id_transp"));
                        telefones.add(telefone);
                    }
                    rs.close();  // Fecha o ResultSet
                    pstmt.close();  // Fecha o PreparedStatement
                }
            } catch (SQLException se) {
                se.printStackTrace();  // Exibe a pilha de erros em caso de exceção
            } finally {
                conexao.desconectar();  // Fecha a conexão
            }
        }
        return telefones;  // Retorna a lista de telefones
    }

    // Método para buscar um telefone pelo ID
    public Telefone buscarTelefone(int id) {
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Comando SQL para buscar um telefone pelo ID
                    PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Telefones WHERE id = ?");
                    pstmt.setInt(1, id);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        // Retorna um objeto Telefone com os dados obtidos
                        return new Telefone(rs.getInt("id"),
                                rs.getString("numero"),
                                rs.getString("id_resp"),
                                rs.getString("id_transp"));
                    }
                }
            } catch (SQLException se) {
                se.printStackTrace();  // Exibe a pilha de erros em caso de exceção
            } finally {
                conexao.desconectar();  // Fecha a conexão
            }
        }
        return null;  // Retorna null se o telefone não for encontrado
    }

    // Método para atualizar os dados de um telefone no banco de dados
    public int atualizarTelefone(Telefone telefone) {
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Comando SQL para atualizar os dados de um telefone
                    PreparedStatement pstmt = connection.prepareStatement(
                            "UPDATE Telefones SET numero = ?, id_transp = ?, id_resp = ? WHERE id = ?");
                    pstmt.setString(1, telefone.getNumero());
                    pstmt.setString(2, telefone.getId_transp());
                    pstmt.setString(3, telefone.getId_resp());
                    pstmt.setInt(4, telefone.getId());
                    return pstmt.executeUpdate();  // Retorna o número de linhas afetadas
                }
            } catch (SQLException se) {
                se.printStackTrace();  // Exibe a pilha de erros em caso de exceção
            } finally {
                conexao.desconectar();  // Fecha a conexão
            }
        }
        return -1;  // Retorna -1 se a operação falhar
    }

    // Método para remover um telefone pelo ID
    public int deletarTelefone(int id) {
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Comando SQL para deletar um telefone pelo ID
                    PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Telefones WHERE id = ?");
                    pstmt.setInt(1, id);
                    return pstmt.executeUpdate();  // Retorna o número de linhas afetadas
                }
            } catch (SQLException se) {
                se.printStackTrace();  // Exibe a pilha de erros em caso de exceção
            } finally {
                conexao.desconectar();  // Fecha a conexão
            }
        }
        return -1;  // Retorna -1 se a operação falhar
    }

    // Método para remover um telefone pelo número
    public int deletarTelefone(String numero) {
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Comando SQL para deletar um telefone pelo número
                    PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Telefones WHERE numero = ?");
                    pstmt.setString(1, numero);
                    return pstmt.executeUpdate();  // Retorna o número de linhas afetadas
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

