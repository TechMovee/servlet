package org.example.projecttechmovee.ClasseTabelas;

public class Admin {
    // Atributos privados da classe Admin
    private int id; // Atributo identificador do Administrador
    private String name; // Nome do Administrador
    private String email; // E-mail do Administrador
    private String senha; // Senha do Administrador

    // Construtor que inicializa todos os atributos da classe Admin
    public Admin(int id, String name,String email, String senha) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.senha = senha;
    }

    // Construtor que inicializa o nome, email e senha; id padrão como -1
    public Admin(String name,String email, String senha) {
        this.id = -1; // Define id como -1 para indicar que ainda não foi atribuído
        this.name = name;
        this.email = email;
        this.senha = senha;
    }

    // Construtor sem atributos
    public Admin() {
    }

    // Métodos GETTERS para acessar os atributos privados

    // Retorna a senha do administrador
    public String getSenha() {return this.senha;}
    // Retorna o Nome do administrador
    public String getName() {return this.name;}
    // Retorna o identificador do administrador
    public int getId() {return this.id;}
    // Retorna o e-mail do administrador
    public String getEmail() {return email;}


    // Métodos SETTERS para modificar os atributos privados

    // Define o identificador do administrador
    public void setId(int id) {this.id = id;}
    // Define o nome do administrador
    public void setName(String name) {this.name = name;}
    // Define a senha do administrador
    public void setSenha(String senha) {this.senha = senha;}
    // Define o e-mail do administrador
    public void setEmail(String email) {this.email = email;}

    // Método toString para representar a classe Admin como uma String
    @Override
    public String toString() {
        return "Administrador: " + "\nID: " + this.id + "\nNome: " + this.name +
                "\nEmail: " + this.email + "\nSenha: " + this.senha;
    }
}
