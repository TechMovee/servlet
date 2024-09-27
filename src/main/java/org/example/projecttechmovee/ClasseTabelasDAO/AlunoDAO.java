package org.example.projecttechmovee.ClasseTabelasDAO;

import org.example.projecttechmovee.ClasseTabelas.Aluno;

import java.sql.*;

public class AlunoDAO {
    private Connection connection;

    // Construtor
    public AlunoDAO(Connection connection) {
        this.connection = connection;
    }

    //    ADICIONAR ALUNO
    public void adicionarAluno(Aluno aluno) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Alunos (id, name, cpf, age, school, turno, pcd, foto, serieEscolar, grauEscolaridade, enderecoId, responsavelCpf) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        pstmt.setInt(1, aluno.getId());
        pstmt.setString(2, aluno.getName());
        pstmt.setString(3, aluno.getCpf());
        pstmt.setInt(4, aluno.getAge());
        pstmt.setString(5, aluno.getSchool());
        pstmt.setString(6, aluno.getTurno());
        pstmt.setString(7, aluno.getPcd());
        pstmt.setString(8, aluno.getFoto());
        pstmt.setString(9, aluno.getSerieEscolar());
        pstmt.setString(10, aluno.getGrauEscolaridade());
        pstmt.setInt(11, aluno.getEnderecoId());
        pstmt.setInt(12, aluno.getResponsavelCpf());
        pstmt.executeUpdate();
    }

    //    Buscar aluno pelo ID
    public Aluno buscarAluno(int id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement( "SELECT * FROM Alunos WHERE id = ?");
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new Aluno(rs.getInt("id"), rs.getString("name"), rs.getString("cpf"), rs.getInt("age"),
                    rs.getString("school"), rs.getString("turno"), rs.getString("pcd"), rs.getString("foto"),
                    rs.getString("serieEscolar"), rs.getString("grauEscolaridade"), rs.getInt("enderecoId"),
                    rs.getInt("responsavelCpf"));
        }
        return null;
    }

    //    Atualizar aluno
    public void atualizarAluno(Aluno aluno) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("UPDATE Alunos SET name = ?, cpf = ?, age = ?, school = ?, turno = ?, pcd = ?, foto = ?, serieEscolar = ?, grauEscolaridade = ?, enderecoId = ?, responsavelCpf = ? WHERE id = ?");
        pstmt.setString(1, aluno.getName());
        pstmt.setString(2, aluno.getCpf());
        pstmt.setInt(3, aluno.getAge());
        pstmt.setString(4, aluno.getSchool());
        pstmt.setString(5, aluno.getTurno());
        pstmt.setString(6, aluno.getPcd());
        pstmt.setString(7, aluno.getFoto());
        pstmt.setString(8, aluno.getSerieEscolar());
        pstmt.setString(9, aluno.getGrauEscolaridade());
        pstmt.setInt(10, aluno.getEnderecoId());
        pstmt.setInt(11, aluno.getResponsavelCpf());
        pstmt.setInt(12, aluno.getId());
        pstmt.executeUpdate();
    }

    //    Remover aluno pelo Id
    public void deletarAluno(int id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Alunos WHERE id = ?");
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
}

