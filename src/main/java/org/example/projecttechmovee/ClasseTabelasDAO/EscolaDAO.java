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
    // Atributo que mantém a conexão com o banco de dados
    private Conexao conexao;

    // Construtor da classe EscolaDAO, que inicializa a conexão
    public EscolaDAO(Conexao conexao) {
        this.conexao = conexao;
    }

    // Método para adicionar uma nova escola ao banco de dados
    public int adicionarEscola(Escolas escola) {
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida
            Connection connection = conexao.getConexao();  // Obtém a conexão
            try {
                if (connection != null) {
                    // Prepara o comando SQL para inserir uma nova escola na tabela Escolas
                    PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Escolas (nome) VALUES (?)");
                    pstmt.setString(1, escola.getNome());  // Define o valor do nome da escola
                    return pstmt.executeUpdate();  // Executa o comando e retorna o número de linhas afetadas
                }
            } catch (SQLException se) {
                se.printStackTrace();  // Exibe a pilha de erros em caso de exceção
            } finally {
                conexao.desconectar();  // Fecha a conexão com o banco
            }
        }
        return -1;  // Retorna -1 se a operação falhar
    }

    // Método para atualizar os dados de uma escola existente no banco de dados
    public int atualizarEscola(Escolas escola) {
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida
            Connection connection = conexao.getConexao();  // Obtém a conexão
            try {
                if (connection != null) {
                    // Prepara o comando SQL para atualizar o nome da escola onde o ID corresponde
                    PreparedStatement pstmt = connection.prepareStatement("UPDATE Escolas SET nome = ? WHERE id = ?");
                    pstmt.setString(1, escola.getNome());
                    pstmt.setInt(2, escola.getId());
                    return pstmt.executeUpdate();  // Executa o comando e retorna o número de linhas alteradas
                }
            } catch (SQLException se) {
                se.printStackTrace();  // Exibe a pilha de erros em caso de exceção
            } finally {
                conexao.desconectar();  // Fecha a conexão com o banco
            }
        }
        return -1;  // Retorna -1 se a operação falhar
    }

    // Método para buscar uma escola pelo ID
    public Escolas buscarEscolaPorId(int id) {
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida
            Connection connection = conexao.getConexao();  // Obtém a conexão
            try {
                if (connection != null) {
                    // Prepara o comando SQL para selecionar a escola onde o ID corresponde
                    PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Escolas WHERE id = ?");
                    pstmt.setInt(1, id);  // Define o ID da escola como parâmetro
                    ResultSet rs = pstmt.executeQuery();  // Executa a consulta e armazena o resultado em um ResultSet

                    if (rs.next()) {
                        // Cria e retorna um novo objeto Escolas com os dados recuperados
                        return new Escolas(rs.getInt("id"), rs.getString("nome"));
                    }
                }
            } catch (SQLException se) {
                se.printStackTrace();  // Exibe a pilha de erros em caso de exceção
            } finally {
                conexao.desconectar();  // Fecha a conexão com o banco
            }
        }
        return null;  // Retorna null se a escola não for encontrada
    }

    // Método para listar todas as escolas do banco de dados
    public List<Escolas> listarEscolas() {
        List<Escolas> escolas = new ArrayList<>();  // Lista para armazenar as escolas
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida
            Connection connection = conexao.getConexao();  // Obtém a conexão
            try {
                if (connection != null) {
                    // Prepara o comando SQL para selecionar todas as escolas da tabela
                    PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Escolas");
                    ResultSet rs = pstmt.executeQuery();  // Executa a consulta

                    // Passa sobre cada registro do ResultSet
                    while (rs.next()) {
                        // Cria um objeto Escolas para cada registro e adiciona à lista
                        Escolas escola = new Escolas(rs.getInt("id"), rs.getString("nome"));
                        escolas.add(escola);
                    }
                    rs.close();  // Fecha o ResultSet
                    pstmt.close();  // Fecha o PreparedStatement
                }
            } catch (SQLException se) {
                se.printStackTrace();  // Exibe a pilha de erros em caso de exceção
            } finally {
                conexao.desconectar();  // Fecha a conexão com o banco
            }
        }
        return escolas;  // Retorna a lista de escolas
    }

    // Método para remover uma escola do banco de dados pelo ID
    public int removerEscola(int id) {
        if (conexao.conectar()) {  // Verifica se a conexão foi estabelecida
            Connection connection = conexao.getConexao();  // Obtém a conexão
            try {
                if (connection != null) {
                    // Prepara o comando SQL para deletar a escola onde o ID corresponde
                    PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Escolas WHERE id = ?");
                    pstmt.setInt(1, id);  // Define o ID da escola que será deletada
                    return pstmt.executeUpdate();  // Executa o comando e retorna o número de linhas alteradas
                }
            } catch (SQLException se) {
                se.printStackTrace();  // Exibe a pilha de erros em caso de exceção
            } finally {
                conexao.desconectar();  // Fecha a conexão com o banco
            }
        }
        return -1;  // Retorna -1 se a operação falhar
    }
}
