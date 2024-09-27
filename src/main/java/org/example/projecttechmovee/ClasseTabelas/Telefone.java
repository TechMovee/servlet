package org.example.projecttechmovee.ClasseTabelas;

public class Telefone {
    private int id;
    private String numero;

//    Construtor

    public Telefone(int id, String numero) {
        this.id = id;
        this.numero = numero;
    }

//    Getters

    public int getId() {return this.id;}
    public String getNumero() {return this.numero;}

//    Setters

    public void setId(int id) {this.id = id;}
    public void setNumero(String numero) {this.numero = numero;}

//    toString

    @Override
    public String toString() {
        return "Telefone{" + "\nid: " + this.id + "\nNumero: " + this.numero;
    }
}
