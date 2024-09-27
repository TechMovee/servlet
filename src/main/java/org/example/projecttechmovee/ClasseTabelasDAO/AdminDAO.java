package org.example.projecttechmovee.ClasseTabelasDAO;

import org.example.projecttechmovee.ClasseTabelas.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    private Connection connection;

    //    Construtor
    public AdminDAO(Connection connection) {
        this.connection = connection;
    }

    //    Adicionar ADMIN
    public void adicionarAdmin(Admin admin) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Admins (id, name, password) VALUES (?, ?, ?)");
        pstmt.setInt(1, admin.getId());
        pstmt.setString(2, admin.getName());
        pstmt.setString(3, admin.getPassword());
        pstmt.executeUpdate();
    }

    // Buscar admin pelo ID
    public Admin buscarAdmin(int id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Admins WHERE id = ?");
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new Admin(rs.getInt("id"), rs.getString("name"), rs.getString("password"));
        }
        return null;
    }

    //    Atualizar Admin
    public void atualizarAdmin(Admin admin) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("UPDATE Admins SET name = ?, password = ? WHERE id = ?");
        pstmt.setString(1, admin.getName());
        pstmt.setString(2, admin.getPassword());
        pstmt.setInt(3, admin.getId());
        pstmt.executeUpdate();
    }

    //    Remover admin pelo ID
    public void deletarAdmin(int id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Admins WHERE id = ?");
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
}
