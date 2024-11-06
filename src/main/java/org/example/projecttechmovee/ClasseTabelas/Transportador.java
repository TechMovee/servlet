package org.example.projecttechmovee.ClasseTabelas;

public class Transportador {

    // Atributos privados da classe Transportador
    private String cnh; // Atributo Identificador do Transportador
    private String nome; // Nome do Transportador
    private String cep; // CEP do Transportador
    private String email; // E-mail do Transportador
    private String senha; // Senha do Transportador
    private String dtNascimento; // Data de Nascimento do Transportador
    private String foto; // Foto do Transportador

    // Construtor que inicializa todos os atributos da classe Transportador
    public Transportador(String cnh, String nome, String cep, String email, String senha, String dtNascimento, String foto) {
        this.cnh = cnh;
        this.nome = nome;
        this.cep = cep;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
        this.foto = foto;
    }

    // Métodos GETTERS para acessar os atributos privados

    // Retorna a CNH do transportador
    public String getCnh() {return this.cnh;}
    // Retorna o nome do transportador
    public String getNome() {return this.nome;}
    // Retorna o CEP do endereço do transportador
    public String getCep() {return this.cep;}
    // Retorna o email do transportador
    public String getEmail() {return this.email;}
    // Retorna a senha do transportador
    public String getSenha() {return this.senha;}
    // Retorna a data de nascimento do transportador
    public String getDtNascimento() {return this.dtNascimento;}
    // Retorna o caminho ou URL da foto do transportador
    public String getFoto() {return this.foto;}

    // Métodos SETTERS para modificar os atributos privados

    // Define o nome do transportador
    public void setNome(String nome) {this.nome = nome;}
    // Define o caminho ou URL da foto do transportador
    public void setFoto(String foto) {this.foto = foto;}
    // Define o CEP do endereço do transportador
    public void setCep(String cep) {this.cep = cep;}
    // Define a senha do transportador
    public void setSenha(String senha) {this.senha = senha;}
    // Define o email do transportador
    public void setEmail(String email) {this.email = email;}


    // Método toString para representar a classe Transportador como uma String
    @Override
    public String toString() {
        return "Transportador: " + "\nCNH: " + this.cnh + "\nNome: " + this.nome +
                "\nCEP: " + this.cep + "\nEmail: " + this.email +
                "\nData de Nascimento: " + this.dtNascimento + "\nFoto: " + this.foto;
    }
}
