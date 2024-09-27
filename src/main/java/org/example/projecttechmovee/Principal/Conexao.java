package org.example.projecttechmovee.Principal;

import java.sql.*;

public class Conexao {
    private Connection conexao;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public boolean conectar() {

        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "1234"
            );
            return true;
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return false;
    }
    public boolean desconectar() {
        try {if (conexao != null && !conexao.isClosed()) {
            conexao.close();
            return true;
        }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }
    public Connection getConexao(){
        return conexao;
    }
}
