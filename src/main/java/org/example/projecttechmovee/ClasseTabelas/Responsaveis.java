package org.example.projecttechmovee.ClasseTabelas;

public class Responsaveis {

    private String cpf;
    private String nome;
    private String dtNascimento;
    private String foto;
    private String senha;
    private String email;

    //  CONSTRUTOR
    public Responsaveis(String cpf, String nome, String dtNascimento, String foto, String senha, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.dtNascimento = dtNascimento;
        this.foto = foto;
        this.senha = senha;
        this.email = email;
    }

    //  GETTERS
    public String getCpf() {return this.cpf;}
    public String getNome() {return this.nome;}
    public String getDtNascimento() {return  this.dtNascimento;}
    public String getFoto() {return this.foto;}
    public String getSenha() {return this.senha;}
    public String getEmail() {return this.email;}


    //  SETTERS
    public void setNome(String nome) {this.nome = nome;}
    public void setFoto(String foto) {this.foto = foto;}
    public void setSenha(String senha) {this.senha = senha;}

    //  toSTRING
    @Override
    public String toString() {
        return "\nResponsaveis: " + "\nCPF: " + this.cpf + "\nNome: " + this.nome + "\nData Nascimento:  "+ this.dtNascimento+ "\nFoto: " + this.foto + "\nSenha: "
                + this.senha + "\nEmail " + this.email;
    }

}
