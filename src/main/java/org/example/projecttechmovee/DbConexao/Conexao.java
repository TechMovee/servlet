package org.example.projecttechmovee.DbConexao;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
public class Conexao {

    private Connection conexao; // Objeto para gerenciar a conexão com o banco de dados

    // Método para estabelecer a conexão com o banco de dados
    public boolean conectar() {
        try {
            // Obtém as credenciais do banco de dados a partir das variáveis de ambiente
            Dotenv dotenv = Dotenv.load();

            String dbUrl = dotenv.get("DB_URL");
            String dbUser = dotenv.get("DB_USER");
            String dbPassword = dotenv.get("DB_PASSWORD");
            String driver = dotenv.get("DB_DRIVER");


            // Registra o driver do PostgreSQL
            Class.forName(driver);

            // Estabelece a conexão com o banco de dados
            conexao = DriverManager.getConnection(
                    dbUrl,
                    dbUser,
                    dbPassword
            );
            return true; // Retorna true se a conexão for bem-sucedida
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace(); // Imprime a stack trace se o driver não for encontrado
        } catch (SQLException sql) {
            sql.printStackTrace(); // Imprime a stack trace se ocorrer um erro de SQL
        }
        return false; // Retorna false se a conexão falhar
    }

    // Método para fechar a conexão com o banco de dados
    public boolean desconectar() {
        try {
            // Verifica se a conexão não é nula e não está fechada
            if (conexao != null && !conexao.isClosed()) {
                conexao.close(); // Fecha a conexão
                return true; // Retorna true se a desconexão for bem-sucedida
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace(); // Imprime a stack trace se ocorrer um erro ao fechar a conexão
        }
        return false; // Retorna false se a desconexão falhar
    }

    // Método para obter a conexão com o banco de dados
    public Connection getConexao() {
        // Verifica se a conexão já foi estabelecida
        if (this.conexao != null) {
            return this.conexao; // Retorna a conexão existente
        } else {
            // Tenta conectar se a conexão não estiver estabelecida
            if (conectar()) {
                return this.conexao; // Retorna a nova conexão
            }
            return null; // Retorna null se não for possível conectar
        }
    }
}