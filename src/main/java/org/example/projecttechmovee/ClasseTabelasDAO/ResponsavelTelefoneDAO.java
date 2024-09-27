package org.example.projecttechmovee.ClasseTabelasDAO;

import java.sql.*;

public class ResponsavelTelefoneDAO {
    private Connection connection;

    //    CONSTRUTOR
    public ResponsavelTelefoneDAO(Connection connection) {
        this.connection = connection;
    }

    //    ADICIONAR NÚMERO DE TELEFONE DE RESPONSÁVEL
    public void adicionarResponsavelTelefone(String cpfResponsavel, int telefoneId) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO ResponsaveisTelefones (cpfResponsavel, telefoneId) VALUES (?, ?)");
        pstmt.setString(1, cpfResponsavel);
        pstmt.setInt(2, telefoneId);
        pstmt.executeUpdate();
        pstmt.close();
    }

    //    REMOVER NÚMERO DE  TELEFONE DE RESPONSÁVEL
    public void deletarResponsavelTelefone(String cpfResponsavel, int telefoneId) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement( "DELETE FROM ResponsaveisTelefones WHERE cpfResponsavel = ? AND telefoneId = ?");
        pstmt.setString(1, cpfResponsavel);
        pstmt.setInt(2, telefoneId);
        pstmt.executeUpdate();
        pstmt.close();
    }
}

