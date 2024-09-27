package org.example.projecttechmovee.ClasseTabelas;

public class Responsaveis {

    private String cpf;
    private String nome;
    private String foto;
    private String senha;
    private int telefone_id;

    //  CONSTRUTOR
    public Responsaveis(String cpf, String nome, String foto, String senha, int telefone_id) {
        this.cpf = cpf;
        this.nome = nome;
        this.foto = foto;
        this.senha = senha;
        this.telefone_id = telefone_id;
    }

    //  GETTERS
    public String getCpf() {return this.cpf;}
    public String getNome() {return this.nome;}
    public String getFoto() {return this.foto;}
    public String getSenha() {return this.senha;}
    public int getTelefone_id() {return this.telefone_id;}

    //  SETTERS
    public void setNome(String nome) {this.nome = nome;}
    public void setFoto(String foto) {this.foto = foto;}
    public void setSenha(String senha) {this.senha = senha;}
    public void setTelefone_id(int telefone_id) {this.telefone_id = telefone_id;}

    //  toSTRING
    @Override
    public String toString() {
        return "\nResponsaveis: " + "\nCPF: " + this.cpf + "\nNome: " + this.nome + "\nFoto: " + this.foto + "\nSenha: "
                + this.senha + "\nTelefone_id: " + this.telefone_id;
    }

}
