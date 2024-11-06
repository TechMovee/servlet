package org.example.projecttechmovee.ClasseTabelas;

public class Responsaveis {

    // Atributos privados da classe Responsaveis
    private String cpf; // Atributo identificar do Responsável
    private String nome; // Nome do Responsável
    private String dtNascimento; // Data de Nascimento do Responsável
    private String foto; // Foto do Responsável
    private String senha; // Senha do Responsável
    private String email; // E-mail do Responsável

    // Construtor que inicializa todos os atributos da classe Responsaveis
    public Responsaveis(String cpf, String nome, String dtNascimento, String foto, String senha, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.dtNascimento = dtNascimento;
        this.foto = foto;
        this.senha = senha;
        this.email = email;
    }

    // Métodos GETTERS para acessar os atributos privados

    // Retorna o CPF do responsável
    public String getCpf() {return this.cpf;}
    // Retorna o nome do responsável
    public String getNome() {return this.nome;}
    // Retorna a data de nascimento do responsável
    public String getDtNascimento() {return this.dtNascimento;}
    // Retorna a foto do responsável
    public String getFoto() {return this.foto;}
    // Retorna a senha do responsável
    public String getSenha() {return this.senha;}
    // Retorna o email do responsável
    public String getEmail() {return this.email;}

    // Métodos SETTERS para modificar os atributos privados

    // Define o nome do responsável
    public void setNome(String nome) {this.nome = nome;}
    // Define a foto do responsável
    public void setFoto(String foto) {this.foto = foto;}
    // Define a senha do responsável
    public void setSenha(String senha) {this.senha = senha;}

    // Método toString para representar a classe Responsaveis como uma String
    @Override
    public String toString() {
        return "\nResponsaveis: " + "\nCPF: " + this.cpf + "\nNome: " + this.nome +
                "\nData Nascimento:  "+ this.dtNascimento+ "\nFoto: " + this.foto +
                "\nSenha: " + this.senha + "\nEmail " + this.email;
    }
}