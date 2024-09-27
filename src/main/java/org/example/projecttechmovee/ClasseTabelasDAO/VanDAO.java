package org.example.projecttechmovee.ClasseTabelasDAO;

import org.example.projecttechmovee.ClasseTabelas.Vans;

import java.sql.*;

public class VanDAO {
    private Connection connection;

    //    CONSTRUTOR
    public VanDAO(Connection connection) {
        this.connection = connection;
    }

    //    ADICIONAR Vans
    public void adicionarVans(Vans Vans) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Vanss (placa, modelo, ano, fotoId, transportadorCnh) VALUES (?, ?, ?, ?, ?)");
        pstmt.setString(1, Vans.getPlaca());
        pstmt.setString(2, Vans.getModelo());
        pstmt.setInt(3, Vans.getAno());
        pstmt.setInt(4, Vans.getFoto_id());
        pstmt.setString(5, Vans.getTransportador_cnh());
        pstmt.executeUpdate();
        pstmt.close();
    }

    //    VER A Vans PELA PLACA
    public Vans buscarVans(String placa) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Vanss WHERE placa = ?");
        pstmt.setString(1, placa);
        ResultSet rs = pstmt.executeQuery();

        Vans Vans = null;
        if (rs.next()) {
            Vans = new Vans(rs.getString("placa"), rs.getString("modelo"), rs.getInt("ano"), rs.getInt("fotoId"), rs.getString("transportadorCnh"));
        }

        rs.close();
        pstmt.close();
        return Vans;
    }

    //    ATUALIZAR Vans
    public void atualizarVans(Vans Vans) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("UPDATE Vanss SET modelo = ?, ano = ?, fotoId = ?, transportadorCnh = ? WHERE placa = ?");
        pstmt.setString(1, Vans.getModelo());
        pstmt.setInt(2, Vans.getAno());
        pstmt.setInt(3, Vans.getFoto_id());
        pstmt.setString(4, Vans.getTransportador_cnh());
        pstmt.setString(5, Vans.getPlaca());
        pstmt.executeUpdate();
        pstmt.close();
    }

    //    EXCLUIR Vans PELA PLACA
    public void deletarVans(String placa) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Vanss WHERE placa = ?");
        pstmt.setString(1, placa);
        pstmt.executeUpdate();
        pstmt.close();
    }
}

