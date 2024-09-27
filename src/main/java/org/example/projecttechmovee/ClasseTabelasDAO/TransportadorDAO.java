package org.example.projecttechmovee.ClasseTabelasDAO;

import org.example.projecttechmovee.ClasseTabelas.Transportador;

import java.sql.*;

public class TransportadorDAO {
    private Connection connection;

    //    CONSTRUTOR
    public TransportadorDAO(Connection connection) {
        this.connection = connection;
    }

    //    ADICIONAR TRANSPORTADOR
    public void adicionarTransportador(Transportador transportador) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Transportadores (cnh, cpf, nome, foto, cep, email, senha) VALUES (?, ?, ?, ?, ?, ?, ?)");
        pstmt.setString(1, transportador.getCnh());
        pstmt.setString(2, transportador.getCpf());
        pstmt.setString(3, transportador.getNome());
        pstmt.setString(4, transportador.getFoto());
        pstmt.setString(5, transportador.getCep());
        pstmt.setString(6, transportador.getEmail());
        pstmt.setString(7, transportador.getSenha());
        pstmt.executeUpdate();
        pstmt.close();
    }

    //    BUSCAR TRANSPORTADOR PELO CPF
    public Transportador buscarTransportador(String cpf) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Transportadores WHERE cpf = ?");
        pstmt.setString(1, cpf);
        ResultSet rs = pstmt.executeQuery();

        Transportador transportador = null;
        if (rs.next()) {
            transportador = new Transportador(rs.getString("cnh"), rs.getString("cpf"), rs.getString("nome"), rs.getString("foto"), rs.getString("cep"), rs.getString("email"), rs.getString("senha"));
        }

        rs.close();
        pstmt.close();
        return transportador;
    }

    //    ATUALIZAR O TRANSPORTADOR
    public void atualizarTransportador(Transportador transportador) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("UPDATE Transportadores SET nome = ?, foto = ?, cep = ?, email = ?, senha = ? WHERE cpf = ?");
        pstmt.setString(1, transportador.getNome());
        pstmt.setString(2, transportador.getFoto());
        pstmt.setString(3, transportador.getCep());
        pstmt.setString(4, transportador.getEmail());
        pstmt.setString(5, transportador.getSenha());
        pstmt.setString(6, transportador.getCpf());
        pstmt.executeUpdate();
        pstmt.close();
    }

    //    REMOVER TRANSPORTADOR pelo CPF
    public void deletarTransportador(String cpf) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Transportadores WHERE cpf = ?");
        pstmt.setString(1, cpf);
        pstmt.executeUpdate();
        pstmt.close();
    }
}

