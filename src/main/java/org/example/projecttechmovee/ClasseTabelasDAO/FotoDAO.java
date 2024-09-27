package org.example.projecttechmovee.ClasseTabelasDAO;

import org.example.projecttechmovee.ClasseTabelas.Foto;

import java.sql.*;

public class FotoDAO {
    private Connection connection;

    //    Construtor
    public FotoDAO(Connection connection) {
        this.connection = connection;
    }

    //    Adicionar foto
    public void adicionarFoto(Foto foto) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Fotos (id, url) VALUES (?, ?)");
        pstmt.setInt(1, foto.getId());
        pstmt.setString(2, foto.getUrl());
        pstmt.executeUpdate();
        pstmt.close();
    }

    //    Buscar foto pelo ID
    public Foto buscarFoto(int id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Fotos WHERE id = ?");
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        Foto foto = null;
        if (rs.next()) {
            foto = new Foto(rs.getInt("id"), rs.getString("url"));
        }

        rs.close();
        pstmt.close();
        return foto;
    }

    //    Atualizar Foto
    public void atualizarFoto(Foto foto) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("UPDATE Fotos SET url = ? WHERE id = ?");
        pstmt.setString(1, foto.getUrl());
        pstmt.setInt(2, foto.getId());
        pstmt.executeUpdate();
        pstmt.close();
    }

    //    Excluir foto pelo ID
    public void deletarFoto(int id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Fotos WHERE id = ?");
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
    }
}

