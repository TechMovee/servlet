package org.example.projecttechmovee.ClasseTabelas;

public class Escolas{
    // Atributos privados da classe Admin
    private int id; // Atributo único da Escola
    private String nome; //Nome da Escola

    // Construtor que inicializa todos os atributos da classe Escolas
    public Escolas(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Construtor que inicializa apenas o nome da escola, por id ser SERIAL no banco de dados
    public Escolas(String nome){
        this.nome = nome;
   }

    // Métodos GETTERS para acessar os atributos privados
    // Retorna o identificador da escola
    public int getId(){return this.id;}
    // Retorna o nome da escola
    public String getNome(){return this.nome;}

    // Método SETTER para modificar os atributos privados

    //Define o nome da escola
    public void setNome(String nome){this.nome = nome;}

    // Método toString para representar a classe Escolas como uma String
    public String toString() {
        return "Escola: " + "\nID: " + this.id +
                "\nNome: " + this.nome;
    }
}