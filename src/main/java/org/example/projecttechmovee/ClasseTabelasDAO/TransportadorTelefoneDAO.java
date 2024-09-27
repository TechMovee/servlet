package org.example.projecttechmovee.ClasseTabelasDAO;

import java.sql.*;

public class TransportadorTelefoneDAO {
    private Connection connection;

    //    CONSTRUTOR
    public TransportadorTelefoneDAO(Connection connection) {
        this.connection = connection;
    }

    //    ADICIONAR TELEFONE DE TRANSPORTADOR
    public void adicionarTransportadorTelefone(String cpfTransportador, int telefoneId) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO TransportadoresTelefones (cpfTransportador, telefoneId) VALUES (?, ?)");
        pstmt.setString(1, cpfTransportador);
        pstmt.setInt(2, telefoneId);

        pstmt.executeUpdate();
        pstmt.close();
    }

    //    REMOVER TELEFONE DE TRANSPORTADOR PELO SEU CPF E ID DE TELEFONE
    public void deletarTransportadorTelefone(String cpfTransportador, int telefoneId) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM TransportadoresTelefones WHERE cpfTransportador = ? AND telefoneId = ?");
        pstmt.setString(1, cpfTransportador);
        pstmt.setInt(2, telefoneId);

        pstmt.executeUpdate();
        pstmt.close();
    }
}

