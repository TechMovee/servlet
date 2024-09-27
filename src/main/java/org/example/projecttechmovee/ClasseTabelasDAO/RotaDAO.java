package org.example.projecttechmovee.ClasseTabelasDAO;

import org.example.projecttechmovee.ClasseTabelas.Rota;

import java.sql.*;

public class RotaDAO {
    private Connection connection;

    //    CONSTRUTOR
    public RotaDAO(Connection connection) {
        this.connection = connection;
    }

    //    ADICIONAR ROTA NOVA
    public void adicionarRota(Rota rota) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Rotas (id, nome, periodo, qntdParadas, tempo, enderecoId) VALUES (?, ?, ?, ?, ?, ?)");
        pstmt.setInt(1, rota.getId());
        pstmt.setString(2, rota.getNome());
        pstmt.setString(3, rota.getPeriodo());
        pstmt.setInt(4, rota.getQntdParadas());
        pstmt.setString(5, rota.getTempo());
        pstmt.setInt(6, rota.getEnderecoId());
        pstmt.executeUpdate();
        pstmt.close();
    }

    //    BUSCAR ROTA PELO ID
    public Rota buscarRota(int id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Rotas WHERE id = ?");
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        Rota rota = null;
        if (rs.next()) {
            rota = new Rota(rs.getInt("id"), rs.getString("nome"), rs.getString("periodo"),
                    rs.getInt("qntdParadas"), rs.getString("tempo"), rs.getInt("enderecoId"));
        }

        rs.close();
        pstmt.close();
        return rota;
    }

    //    ATUALIZAR ROTA PARA OUTRA
    public void atualizarRota(Rota rota) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("UPDATE Rotas SET nome = ?, periodo = ?, qntdParadas = ?, tempo = ?, enderecoId = ? WHERE id = ?");
        pstmt.setString(1, rota.getNome());
        pstmt.setString(2, rota.getPeriodo());
        pstmt.setInt(3, rota.getQntdParadas());
        pstmt.setString(4, rota.getTempo());
        pstmt.setInt(5, rota.getEnderecoId());
        pstmt.setInt(6, rota.getId());
        pstmt.executeUpdate();
        pstmt.close();
    }

    //    REMOVER ROTAS PELO ID
    public void deletarRota(int id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Rotas WHERE id = ?");
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
    }
}

