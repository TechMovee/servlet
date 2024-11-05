package org.example.projecttechmovee.ClasseTabelasDAO;

import org.example.projecttechmovee.ClasseTabelas.Admin;
import org.example.projecttechmovee.DbConexao.Conexao;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    // Atributo privado que representa a conexão com o banco de dados
    private Conexao conexao;

    // Construtor da classe AdminDAO, que inicializa o objeto de conexão recebido
    public AdminDAO(Conexao conexao) {
        this.conexao = conexao;
    }

    // Método para adicionar um novo administrador ao banco de dados
    public int adicionarAdmin(Admin admin){

        // Verifica se a conexão com o banco de dados foi estabelecida
        if(conexao.conectar()) {
            Connection connection = conexao.getConexao(); // Obtém a conexão
            try {
                if (connection != null) {
                    PreparedStatement pstmt = null;
                    // Prepara o comando SQL para inserir um novo administrador na tabela Admins
                    pstmt = connection.prepareStatement("INSERT INTO Admins (nome, email, senha) VALUES (?, ?, ?)");

                    // Define os valores dos parâmetros da consulta SQL a partir dos atributos do objeto admin
                    pstmt.setString(1, admin.getName());
                    pstmt.setString(2, admin.getEmail());
                    pstmt.setString(3, admin.getSenha());

                    // Executa o comando SQL e retorna o número de linhas afetadas
                    return pstmt.executeUpdate();
                }
            } catch (SQLException se) {
                se.printStackTrace(); // Exibe a pilha de erros caso ocorra uma exceção de SQL
            } finally {
                // Desconecta do banco de dados, garantindo que a conexão será fechada
                conexao.desconectar();
            }
        }
        // Retorna -1 se a inserção falhar
        return -1;
    }

    // Método para buscar todos os administradores no banco de dados e retornar como uma lista de objetos Admin
    public List<Admin> listarAdmins() {
        List<Admin> admins = new ArrayList<>(); // Cria uma lista para armazenar os administradores

        // Verifica se a conexão com o banco de dados foi estabelecida
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao(); // Obtém a conexão com o banco
            try {
                if (connection != null) {
                    // Prepara o comando SQL para selecionar todos os registros da tabela Admins
                    PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Admins");
                    ResultSet rs = pstmt.executeQuery(); // Executa a consulta e armazena o resultado em um ResultSet

                    // Passa sobre cada registro do ResultSet
                    while (rs.next()) {
                        // Cria um objeto Admin para cada registro do ResultSet, inicializando com os dados do banco
                        Admin admin = new Admin(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("senha")
                        );
                        admins.add(admin); // Adiciona o objeto 'Admin' à lista de administradores
                    }
                    rs.close(); // Fecha o ResultSet
                    pstmt.close(); // Fecha o PreparedStatement
                }
            } catch (SQLException se) {
                se.printStackTrace(); // Exibe a pilha de erros caso ocorra uma exceção de SQL
            } finally {
                conexao.desconectar(); // Garante que a conexão seja fechada após a operação
            }
        }
        return admins; // Retorna a lista de administradores
    }



    // Método para buscar um administrador específico no banco de dados pelo ID
    public Admin buscarAdmin(int id) {

        // Verifica se a conexão com o banco de dados foi estabelecida
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao(); // Obtém a conexão
            try {
                if (connection != null) {
                    // Prepara o comando SQL para selecionar o administrador pelo ID
                    PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Admins WHERE id = ?");
                    pstmt.setInt(1, id); // Define o ID do administrador como parâmetro para a consulta
                    ResultSet rs = pstmt.executeQuery(); // Executa a consulta e armazena o resultado em um ResultSet

                    // Verifica se o ResultSet contém algum registro
                    if (rs.next()) {
                        // Retorna um novo objeto Admin inicializado com os dados do banco de dados
                        return new Admin(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("senha")
                        );
                    }
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar();
            }
        }
        return null; // Retorna null se o administrador não for encontrado
    }

    // Método para buscar um administrador específico no banco de dados pelo E-mail
    public Admin buscarAdmin(String email){

        // Verifica se a conexão com o banco de dados foi estabelecida
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Prepara o comando SQL para selecionar o administrador pelo email
                    PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Admins WHERE email = ?");
                    pstmt.setString(1, email); // Define o email do administrador como parâmetro para a consulta
                    ResultSet rs = pstmt.executeQuery();

                    // Verifica se o ResultSet contém algum registro
                    if (rs.next()) {
                        // Cria e retorna um objeto Admin com os dados do banco de dados
                        return new Admin(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("senha")
                        );
                    }
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar();
            }
        }
        return null; // Retorna null se o administrador não for encontrado
    }

    // Método para atualizar os dados de um administrador existente no banco de dados
    public int atualizarAdmin(Admin admin) {
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Prepara o comando SQL para atualizar um administrador pelo ID
                    PreparedStatement pstmt = connection.prepareStatement("UPDATE Admins SET nome = ?, senha  = ?, email = ? WHERE id = ?");
                    pstmt.setString(1, admin.getName()); // Define o novo nome do administrador
                    pstmt.setString(2, admin.getSenha()); // Define a nova senha
                    pstmt.setString(3, admin.getEmail()); // Define o novo email
                    pstmt.setInt(4, admin.getId());       // Define o ID do administrador a ser atualizado

                    // Executa a atualização e retorna o número de linhas afetadas
                    return pstmt.executeUpdate();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar();
            }
        }
        return -1; // Retorna -1 se a atualização falhar
    }

    // Método para remover um administrador do banco de dados pelo ID
    public int deletarAdmin(int id) {
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Prepara o comando SQL para deletar um administrador pelo ID
                    PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Admins WHERE id = ?");
                    pstmt.setInt(1, id); // Define o ID do administrador a ser deletado

                    // Executa o comando de exclusão e retorna o número de linhas alteradas
                    return pstmt.executeUpdate();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar();
            }
        }
        return -1; // Retorna -1 se a exclusão falhar
    }

    // Método para remover um administrador do banco de dados pelo E-mail
    public int deletarAdmin(String email) {
        if (conexao.conectar()) {
            Connection connection = conexao.getConexao();
            try {
                if (connection != null) {
                    // Prepara o comando SQL para deletar um administrador pelo E-mail
                    PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Admins WHERE email = ?");
                    pstmt.setString(1, email); // Define o email do administrador a ser deletado

                    // Executa o comando de exclusão e retorna o número de linhas alteradas
                    return pstmt.executeUpdate();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                conexao.desconectar();
            }
        }
        return -1; // Retorna -1 se a exclusão falhar
    }
}