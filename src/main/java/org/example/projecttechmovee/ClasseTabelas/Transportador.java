package org.example.projecttechmovee.ClasseTabelas;

public class Transportador {
    private String cnh;
    private String nome;
    private String cep;
    private String email;
    private String senha;
    private String dtNascimento;
    private String foto;

//    Construtor

    public Transportador(String cnh, String nome, String cep, String email, String senha, String dtNascimento, String foto) {
        this.cnh = cnh;
        this.nome = nome;
        this.cep = cep;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
        this.foto = foto;
    }


//    Getters

    public String getCnh() {
        return this.cnh;
    }
    public String getNome() {
        return this.nome;
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
    public String getDtNascimento() {
        return this.dtNascimento;
    }
    public String getFoto() {
        return this.foto;
    }

    //    Setters

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setEmail(String email) {this.email = email;}

    //    toString


    @Override
    public String toString() {
        return "Transportador{" +
                "nome='" + this.nome + '\'' +
                ", email='" + this.email + '\'' +
                ", dtNascimento='" + this.dtNascimento + '\'' +
                ", foto='" + this.foto + '\''+
                '}';
    }
}
