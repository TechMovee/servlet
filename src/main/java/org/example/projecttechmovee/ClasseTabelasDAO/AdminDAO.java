package org.example.projecttechmovee.ClasseTabelasDAO;

import org.example.projecttechmovee.ClasseTabelas.Admin;
import org.example.projecttechmovee.DbConexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    // Atributo que mantém a conexão com o banco de dados
    private final Conexao conexao;

    // Construtor da classe AdminDAO, que inicializa a conexão
    public AdminDAO(Conexao conexao) {
        this.conexao = conexao;
    }

    // Método para adicionar um novo administrador no banco de dados
    public int adicionarAdmin(Admin admin){
        // Tenta conectar ao banco de dados
        if(conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Prepara a consulta SQL para inserir um novo administrador com nome, email e senha
                    PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Admins (nome, email, senha) VALUES (?, ?, ?)");
                    pstmt.setString(1, admin.getName());
                    pstmt.setString(2, admin.getEmail());
                    pstmt.setString(3, admin.getSenha());
                    return pstmt.executeUpdate(); // Executa a inserção e retorna o resultado
                }
            } catch (SQLException se) {
                se.printStackTrace(); // Mostra o erro no console em caso de falha
            } finally {
                conexao.desconectar(); // Garante que a conexão seja fechada
            }
        }
        return -1; // Retorna -1 caso a inserção não tenha sido bem-sucedida
    }

    // Método para buscar todos os ADMINs cadastrados no banco de dados
    public List<Admin> listarAdmins() {
        List<Admin> admins = new ArrayList<>(); // Lista para armazenar os ADMINs
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao();

            try {
                if (connection != null) {
                    // Prepara a consulta SQL para selecionar todos os ADMINs
                    PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Admins");
                    ResultSet rs = pstmt.executeQuery();

                    while (rs.next()) {
                        // Cria um objeto Admin para cada linha encontrada no banco de dados
                        Admin admin = new Admin(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("senha"));
                        admins.add(admin); // Adiciona o ADMIN à lista
                    }
                    rs.close(); // Fecha o ResultSet
                    pstmt.close(); // Fecha o PreparedStatement
                }
            } catch (SQLException se) {
                se.printStackTrace(); // Exibe o erro no console em caso de exceção
            } finally {
                conexao.desconectar(); // Garante que a conexão seja fechada
            }
        }
        return admins; // Retorna a lista de ADMINs
    }

    // Método para buscar um ADMIN pelo ID
    public Admin buscarAdmin(int id){
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Prepara a consulta SQL para buscar um ADMIN específico pelo ID
                    PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Admins WHERE id = ?");
                    pstmt.setInt(1, id);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        // Retorna o ADMIN caso seja encontrado no banco de dados
                        return new Admin(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
                    }
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar(); // Garante que a conexão seja fechada
            }
        }
        return null; // Retorna null caso o ADMIN não seja encontrado
    }

    // Método para buscar um ADMIN pelo email
    public Admin buscarAdmin(String email){
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Prepara a consulta SQL para buscar um ADMIN específico pelo email
                    PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Admins WHERE email = ?");
                    pstmt.setString(1, email);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        // Retorna o ADMIN caso seja encontrado no banco de dados
                        return new Admin(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
                    }
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar(); // Garante que a conexão seja fechada
            }
        }
        return null; // Retorna null caso o ADMIN não seja encontrado
    }

    // Método para atualizar os dados de um ADMIN existente no banco de dados
    public int atualizarAdmin(Admin admin) {
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Prepara a consulta SQL para atualizar os dados do ADMIN
                    PreparedStatement pstmt = connection.prepareStatement("UPDATE Admins SET nome = ?, senha  = ?, email = ? WHERE id = ?");
                    pstmt.setString(1, admin.getName());
                    pstmt.setString(2, admin.getSenha());
                    pstmt.setString(3, admin.getEmail());
                    pstmt.setInt(4, admin.getId());
                    return pstmt.executeUpdate(); // Executa a atualização e retorna o resultado
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar(); // Garante que a conexão seja fechada
            }
        }
        return -1; // Retorna -1 caso a atualização não tenha sido bem-sucedida
    }

    // Método para remover um ADMIN pelo ID
    public int deletarAdmin(int id){
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Prepara a consulta SQL para deletar um ADMIN específico pelo ID
                    PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Admins WHERE id = ?");
                    pstmt.setInt(1, id);
                    return pstmt.executeUpdate(); // Executa a remoção e retorna o resultado
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar(); // Garante que a conexão seja fechada
            }
        }
        return -1; // Retorna -1 caso a remoção não tenha sido bem-sucedida
    }
}