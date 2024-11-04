package org.example.projecttechmovee.ClasseTabelas;

public class Telefone {
    private int id;
    private String numero;
    private String id_transp;
    private String id_resp;

//    Construtor

    public Telefone(int id, String numero, String id_resp, String id_transp){
        this.id = id;
        this.numero = numero;
        this.id_resp = id_resp;
        this.id_transp = id_transp;
    }

//    Getters

    public int getId() {return this.id;}
    public String getNumero() {return this.numero;}
    public String getId_transp() {return this.id_transp;}
    public String getId_resp() {return this.id_resp;}

//    Setters
    public void setNumero(String numero) {this.numero = numero;}

    public String toString() {
        return "Telefone{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", id_transp=" + id_transp +
                ", id_resp=" + id_resp +
                '}';
    }
}
