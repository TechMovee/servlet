package org.example.projecttechmovee.ClasseTabelasDAO;

import org.example.projecttechmovee.ClasseTabelas.Telefone;
import org.example.projecttechmovee.Principal.Conexao;

import java.sql.*;

public class TelefoneDAO {
    private Conexao conexao;

    //    CONSTRUTOR
    public TelefoneDAO(Conexao connection) {
        this.conexao = connection;
    }

    //    ADICIONAR TELEFONE
    public void adicionarTelefone(Telefone telefone) throws SQLException {
        PreparedStatement pstmt = conexao.getConexao().prepareStatement("INSERT INTO Telefones (id, numero) VALUES (?, ?)");
        pstmt.setInt(1, telefone.getId());
        pstmt.setString(2, telefone.getNumero());
        pstmt.executeUpdate();
        pstmt.close();
    }

    //    BUSCAR TELEFONE PELO ID DO DONO
    public Telefone buscarTelefone(int id) throws SQLException {
        PreparedStatement pstmt = conexao.getConexao().prepareStatement("SELECT * FROM Telefones WHERE id = ?");
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        Telefone telefone = null;
        if (rs.next()) {
            telefone = new Telefone(rs.getInt("id"), rs.getString("numero"));
        }

        rs.close();
        pstmt.close();
        return telefone;
    }

    //    ATUALIZAR NÚMERO DE TELEFONE
    public void atualizarTelefone(Telefone telefone) throws SQLException {
        PreparedStatement pstmt = conexao.getConexao().prepareStatement("UPDATE Telefones SET numero = ? WHERE id = ?");
        pstmt.setString(1, telefone.getNumero());
        pstmt.setInt(2, telefone.getId());
        pstmt.executeUpdate();
        pstmt.close();
    }

    //    EXCLUIR NÚMERO DE TELEFONE pelo ID
    public void deletarTelefone(int id) throws SQLException {
        PreparedStatement pstmt = conexao.getConexao().prepareStatement( "DELETE FROM Telefones WHERE id = ?");
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
    }
}

