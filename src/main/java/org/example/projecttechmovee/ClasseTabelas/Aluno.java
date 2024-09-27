package org.example.projecttechmovee.ClasseTabelas;

public class Aluno {
    private int id;
    private String name;
    private String cpf;
    private int age;
    private String school;
    private String turno;
    private String pcd;
    private String foto;
    private String serieEscolar;
    private String grauEscolaridade;
    private int enderecoId;
    private int responsavelCpf;

    //    CONSTRUTOR
    public Aluno(int id, String name, String cpf, int age, String school, String turno, String pcd, String foto, String serieEscolar, String grauEscolaridade, int enderecoId, int responsavelCpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.age = age;
        this.school = school;
        this.turno = turno;
        this.pcd = pcd;
        this.foto = foto;
        this.serieEscolar = serieEscolar;
        this.grauEscolaridade = grauEscolaridade;
        this.enderecoId = enderecoId;
        this.responsavelCpf = responsavelCpf;
    }

    //    GETTERS
    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getCpf() {
        return this.cpf;
    }
    public int getAge() {
        return this.age;
    }
    public String getSchool() {
        return this.school;
    }
    public String getTurno() {
        return this.turno;
    }
    public String getPcd() {
        return this.pcd;
    }
    public String getFoto() {
        return this.foto;
    }
    public String getSerieEscolar() {
        return this.serieEscolar;
    }
    public String getGrauEscolaridade() {
        return this.grauEscolaridade;
    }
    public int getEnderecoId() {
        return this.enderecoId;
    }
    public int getResponsavelCpf() {
        return this.responsavelCpf;
    }

    //    SETTERS
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public void setTurno(String turno) {
        this.turno = turno;
    }
    public void setPcd(String pcd) {
        this.pcd = pcd;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public void setSerieEscolar(String serieEscolar) {
        this.serieEscolar = serieEscolar;
    }
    public void setGrauEscolaridade(String grauEscolaridade) {
        this.grauEscolaridade = grauEscolaridade;
    }
    public void setEnderecoId(int enderecoId) {
        this.enderecoId = enderecoId;
    }
    public void setResponsavelCpf(int responsavelCpf) {
        this.responsavelCpf = responsavelCpf;
    }

//   toString

    @Override
    public String toString() {
        return "\nAluno: " + "\nID: " + this.id + "\nName: " + this.name + "\nCPF: " + this.cpf  + "\nAge:" + this.age + "\nSchool: " + this.school
                + "\nTurno: " + this.turno  + "\nPcd: " + this.pcd  + "\nFoto" + this.foto  + "\nSerieEscolar: " + this.serieEscolar +
                "\nGrau de escolaridade:" + this.grauEscolaridade + "\nEnderecoId: " + this.enderecoId + "\nresponsavelCpf: " +
                this.responsavelCpf;
    }
}
