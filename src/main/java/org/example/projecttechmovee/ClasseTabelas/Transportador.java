package org.example.projecttechmovee.ClasseTabelas;

public class Transportador {
    private String cnh;
    private String cpf;
    private String nome;
    private String foto;
    private String cep;
    private String email;
    private String senha;

//    Construtor

    public Transportador(String cnh, String cpf, String nome, String foto, String cep, String email, String senha) {
        this.cnh = cnh;
        this.cpf = cpf;
        this.nome = nome;
        this.foto = foto;
        this.cep = cep;
        this.email = email;
        this.senha = senha;
    }

//    Getters

    public String getCnh() {
        return this.cnh;
    }
    public String getCpf() {
        return this.cpf;
    }
    public String getNome() {
        return this.nome;
    }
    public String getFoto() {
        return this.foto;
    }
    public String getCep() {
        return this.cep;
    }
    public String getEmail() {
        return this.email;
    }
    public String getSenha() {
        return this.senha;
    }

//    Setters

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

//    toString

    @Override
    public String toString() {
        return "\nCNH: " + this.cnh + "\nNome: " + this.nome + "\nFoto: " + this.foto + "\nCEP:" + this.cep +
                "\nEmail: " + this.email + "\nSenha: " + this.senha;
    }
}
