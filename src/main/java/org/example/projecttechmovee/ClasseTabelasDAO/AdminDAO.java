package org.example.projecttechmovee.ClasseTabelasDAO;

import org.example.projecttechmovee.ClasseTabelas.Admin;
import org.example.projecttechmovee.DbConexao.Conexao;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    private Conexao conexao;

    //    Construtor
    public AdminDAO(Conexao conexao) {
        this.conexao = conexao;
    }

    //    Adicionar ADMIN
    public int adicionarAdmin(Admin admin){
        if(conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = null;
                    pstmt = connection.prepareStatement("INSERT INTO Admins (nome, email, senha) VALUES (?, ?, ?)");
                    pstmt.setString(1, admin.getName());
                    pstmt.setString(2, admin.getEmail());
                    pstmt.setString(3, admin.getSenha());
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

    //Buscar todos Admins
    public List<Admin> listarAdmins() {
        List<Admin> admins = new ArrayList<>();
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao(); // Obtenha a conexão aqui

            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Admins");
                    ResultSet rs = pstmt.executeQuery();

                    while (rs.next()) {
                        Admin admin = new Admin(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("senha"));
                        admins.add(admin);
                    }
                    rs.close();
                    pstmt.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar(); // Garante que a conexão seja fechada
            }
        }
        return admins; // Retorna a lista de admins
    }


    // Buscar admin pelo ID
    public Admin buscarAdmin(int id){
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Admins WHERE id = ?");
                    pstmt.setInt(1, id);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        return new Admin(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
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

//    Buscar Admin pelo Email
    public Admin buscarAdmin(String email){
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Admins WHERE email = ?");
                    pstmt.setString(1, email);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        Admin temp = new Admin(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
                        return temp;
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

    //    Atualizar Admin
    public int atualizarAdmin(Admin admin) {
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement("UPDATE Admins SET nome = ?, senha  = ?, email = ? WHERE id = ?");
                    pstmt.setString(1, admin.getName());
                    pstmt.setString(2, admin.getSenha());
                    pstmt.setString(3, admin.getEmail());
                    pstmt.setInt(4, admin.getId());
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

    //    Remover admin pelo ID
    public int deletarAdmin(int id){
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Admins WHERE id = ?");
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

//    Deletar Admin pelo Email
    public int deletarAdmin(String email){
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Admins WHERE email = ?");
                    pstmt.setString(1, email);
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
