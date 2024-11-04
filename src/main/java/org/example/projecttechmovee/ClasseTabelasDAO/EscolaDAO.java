package org.example.projecttechmovee.ClasseTabelasDAO;

import org.example.projecttechmovee.ClasseTabelas.Escolas;
import org.example.projecttechmovee.DbConexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class EscolaDAO {
    private Conexao conexao;
    // Construtor
    public EscolaDAO(Conexao conexao) {
        this.conexao = conexao;
    }

    // Adicionar uma escola
    public int adicionarEscola(Escolas escola){
        if(conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement(
                            "INSERT INTO Escolas (nome) VALUES (?)"
                    );
                    pstmt.setString(1, escola.getNome());

                    return pstmt.executeUpdate();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar();
            }
        }
        return -1;
    }

    // Atualizar uma escola
    public int atualizarEscola(Escolas escola){
        if(conexao.conectar()) {

            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement(
                            "UPDATE Escolas SET nome = ? WHERE id = ?"
                    );
                    pstmt.setString(1, escola.getNome());
                    pstmt.setInt(2, escola.getId());

                    return pstmt.executeUpdate();
                }
            } catch (SQLException se) {
                se.printStackTrace();
                return -1;
            } finally {
                conexao.desconectar();
            }
        }
        return -1;
    }

    // Buscar uma escola por ID
    public Escolas buscarEscolaPorId(int id){
        if(conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement(
                            "SELECT * FROM Escolas WHERE id = ?"
                    );
                    pstmt.setInt(1, id);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                         return  new Escolas(rs.getInt("id"), rs.getString("nome"));
                    }
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar();
            }

        }
        return null;
    }

    // Listar todas as escolas
    public List<Escolas> listarEscolas() {
        List<Escolas> escolas = new ArrayList<>();
        if(conexao.conectar()) {

            Connection connection = conexao.getConexao(); // Obtenha a conexão aqui

            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Escolas");
                    ResultSet rs = pstmt.executeQuery();

                    while (rs.next()) {
                        Escolas escola = new Escolas(rs.getInt("id"), rs.getString("nome"));
                        escolas.add(escola);
                    }
                    rs.close();
                    pstmt.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar(); // Garante que a conexão seja fechada
            }
        }
        return escolas; // Retorna a lista de escolas
    }


    // Remover uma escola
    public int removerEscola(int id){
        if(conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    PreparedStatement pstmt = connection.prepareStatement(
                            "DELETE FROM Escolas WHERE id = ?"
                    );
                    pstmt.setInt(1, id);

                    return pstmt.executeUpdate();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar();
            }
        }
        return -1;
    }
}
