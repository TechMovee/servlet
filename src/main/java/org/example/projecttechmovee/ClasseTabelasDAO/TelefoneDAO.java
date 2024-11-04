package org.example.projecttechmovee.ClasseTabelasDAO;

import org.example.projecttechmovee.ClasseTabelas.Telefone;
import org.example.projecttechmovee.DbConexao.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelefoneDAO {
    private Conexao conexao;

    // Construtor
    public TelefoneDAO(Conexao conexao) {
        this.conexao = conexao;
    }

    // Adicionar Telefone
    public int adicionarTelefone(Telefone telefone) {
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement(
                            "INSERT INTO Telefones (numero, id_transp, id_resp) VALUES (?, ?, ?)"
                    );
                    pstmt.setString(1, telefone.getNumero());
                    pstmt.setString(2, telefone.getId_transp());
                    pstmt.setString(3, telefone.getId_resp());
                    return pstmt.executeUpdate();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar();
            }
        }
        return -1;
    }

    // Buscar todos Telefones
    public List<Telefone> listarTelefones() {
        List<Telefone> telefones = new ArrayList<>();
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao();

            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Telefones");
                    ResultSet rs = pstmt.executeQuery();

                    while (rs.next()) {
                        Telefone telefone = new Telefone(
                                rs.getInt("id"),
                                rs.getString("numero"),
                                rs.getString("id_resp"),
                                rs.getString("id_transp"));
                        telefones.add(telefone);
                    }
                    rs.close();
                    pstmt.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar();
            }
        }
        return telefones;
    }

    // Buscar Telefone pelo ID
    public Telefone buscarTelefone(int id) {
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Telefones WHERE id = ?");
                    pstmt.setInt(1, id);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        return new Telefone(rs.getInt("id"), rs.getString("numero"), rs.getString("id_resp"), rs.getString("id_transp"));
                    }
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar();
            }
        }
        return null;
    }

    // Atualizar Telefone
    public int atualizarTelefone(Telefone telefone) {
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement(
                            "UPDATE Telefones SET numero = ?, id_transp = ?, id_resp = ? WHERE id = ?");
                    pstmt.setString(1, telefone.getNumero());
                    pstmt.setString(2, telefone.getId_transp());
                    pstmt.setString(3, telefone.getId_resp());
                    pstmt.setInt(4, telefone.getId());
                    return pstmt.executeUpdate();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar();
            }
        }
        return -1;
    }

    // Remover Telefone pelo ID
    public int deletarTelefone(int id) {
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Telefones WHERE id = ?");
                    pstmt.setInt(1, id);
                    return pstmt.executeUpdate();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar();
            }
        }
        return -1;
    }

    // Remover Telefone pelo Número
    public int deletarTelefone(String numero) {
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Telefones WHERE numero = ?");
                    pstmt.setString(1, numero);
                    return pstmt.executeUpdate();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar();
            }
        }
        return -1;
    }
}

