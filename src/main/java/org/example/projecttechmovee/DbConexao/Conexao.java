package org.example.projecttechmovee.DbConexao;

import java.sql.*;
public class Conexao {

    private Connection conexao;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public boolean conectar() {

        try {
            String dbUrl = System.getenv("DB_URL");
            String dbUser = System.getenv("DB_USER");
            String dbPassword = System.getenv("DB_PASSWORD");
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(
                    dbUrl,
                    dbUser,
                    dbPassword
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
        if (this.conexao!=null) {
            return this.conexao;
        }else {
            if (conectar()){
                return this.conexao;
            }
            return null;
        }
    }
}
