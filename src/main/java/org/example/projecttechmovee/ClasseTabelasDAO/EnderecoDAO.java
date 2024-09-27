package org.example.projecttechmovee.ClasseTabelasDAO;

import org.example.projecttechmovee.ClasseTabelas.Endereco;
import org.example.projecttechmovee.Principal.Conexao;

import java.sql.*;

public class EnderecoDAO {
    private Conexao conexao;

    // Construtor
    public EnderecoDAO(Conexao conexao) {
        this.conexao = conexao;
    }

    // Adicionar endereço
    public int adicionarEndereco(Endereco endereco) {
        try {
            PreparedStatement pstmt = conexao.getConexao().prepareStatement("INSERT INTO Enderecos (id, cep, bairro, rua, numero) VALUES (?, ?, ?, ?, ?)");
            pstmt.setInt(1, endereco.getId());
            pstmt.setString(2, endereco.getCep());
            pstmt.setString(3, endereco.getBairro());
            pstmt.setString(4, endereco.getRua());
            pstmt.setString(5, endereco.getNumero());
            pstmt.executeUpdate();
            return 1; // Sucesso
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Exceção
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Erro inesperado
        }
    }

    // Buscar endereço pelo ID
    public Endereco buscarEndereco(int id) {
        try {
            PreparedStatement pstmt = conexao.getConexao().prepareStatement("SELECT * FROM Enderecos WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Endereco(rs.getInt("id"), rs.getString("cep"), rs.getString("bairro"), rs.getString("rua"), rs.getString("numero"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Se não encontrar ou houver erro
    }

    // Atualizar Endereço
    public int atualizarEndereco(Endereco endereco) {
        try {
            PreparedStatement pstmt = conexao.getConexao().prepareStatement("UPDATE Enderecos SET cep = ?, bairro = ?, rua = ?, numero = ? WHERE id = ?");
            pstmt.setString(1, endereco.getCep());
            pstmt.setString(2, endereco.getBairro());
            pstmt.setString(3, endereco.getRua());
            pstmt.setString(4, endereco.getNumero());
            pstmt.setInt(5, endereco.getId());
            pstmt.executeUpdate();
            return 1; // Sucesso
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Exceção
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Erro inesperado
        }
    }

    // Remover endereço pelo ID
    public int deletarEndereco(int id) {
        try {
            PreparedStatement pstmt = conexao.getConexao().prepareStatement("DELETE FROM Enderecos WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return 1; // Sucesso
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Exceção
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Erro inesperado
        }
    }
}

