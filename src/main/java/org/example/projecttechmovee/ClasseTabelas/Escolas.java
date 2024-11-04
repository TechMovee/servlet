package org.example.projecttechmovee.ClasseTabelas;

public class Escolas{
    private int id;
    private String nome;

    // Construtor
    public Escolas(int id, String nome) {
        this.id = id;
        this.nome = nome;
   }

   public Escolas(String nome){
        this.nome = nome;
   }

    //Getters
    public int getId(){return this.id;}

    public String getNome(){return this.nome;}

    //Set
    public void setNome(String nome){this.nome = nome;}

    public String toString() {
        return "\nID: " + this.id + "\nNome: " + this.nome;
    }
}
