package org.example.projecttechmovee.ClasseTabelasDAO;

import org.example.projecttechmovee.ClasseTabelas.Responsaveis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResponsavelDAO {
    private Connection connection;

    //    Construtor
    public ResponsavelDAO(Connection connection) {
        this.connection = connection;
    }

    // Adicionar um responsável
    public void adicionarResponsavel(Responsaveis responsavel) {
        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO responsaveis (cpf, nome, foto, senha, telefone_id) VALUES (?, ?, ?, ?, ?)")) {
            pstmt.setString(1, responsavel.getCpf());
            pstmt.setString(2, responsavel.getNome());
            pstmt.setString(3, responsavel.getFoto());
            pstmt.setString(4, responsavel.getSenha());
            pstmt.setInt(5, responsavel.getTelefone_id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir responsável: " + e.getMessage());
        }
    }

    // Atualizar um responsável
    public void atualizarResponsavel(Responsaveis responsavel) {
        try (PreparedStatement pstmt = connection.prepareStatement("UPDATE responsaveis SET nome = ?, foto = ?, senha = ?, telefone_id = ? WHERE cpf = ?")) {
            pstmt.setString(1, responsavel.getNome());
            pstmt.setString(2, responsavel.getFoto());
            pstmt.setString(3, responsavel.getSenha());
            pstmt.setInt(4, responsavel.getTelefone_id());
            pstmt.setString(5, responsavel.getCpf());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar responsável: " + e.getMessage());
        }
    }

    // Excluir um responsável pelo CPF
    public void deletarResponsavel(String cpf) {
        try (PreparedStatement pstmt = connection.prepareStatement( "DELETE FROM responsaveis WHERE cpf = ?")) {
            pstmt.setString(1, cpf);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir responsável: " + e.getMessage());
        }
    }

    // Buscar Responsável pelo CPF
    public Responsaveis buscarResponsavel(String cpf) {
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM responsaveis WHERE cpf = ?")) {
            pstmt.setString(1, cpf);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Responsaveis(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("foto"),
                        rs.getString("senha"),
                        rs.getInt("telefone_id")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar responsável: " + e.getMessage());
        }
        return null;
    }

    // Listar Responsáveis
    public List<Responsaveis> listarResponsaveis() {
        List<Responsaveis> responsaveisList = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement( "SELECT * FROM responsaveis")) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Responsaveis responsavel = new Responsaveis(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("foto"),
                        rs.getString("senha"),
                        rs.getInt("telefone_id")
                );
                responsaveisList.add(responsavel);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar responsáveis: " + e.getMessage());
        }
        return responsaveisList;
    }
}

