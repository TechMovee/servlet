package org.example.projecttechmovee.ClasseTabelas;

public class Telefone {

    // Atributos privados da classe Telefone
    private int id; // Identificador único do telefone
    private String numero; // Número do telefone
    private String id_transp; // Identificador do transportador associado (caso exista)
    private String id_resp; // Identificador do responsável associado (caso exista)

    // Construtor que inicializa todos os atributos da classe Telefone
    public Telefone(int id, String numero, String id_resp, String id_transp){
        this.id = id;
        this.numero = numero;
        this.id_resp = id_resp;
        this.id_transp = id_transp;
    }

    // Métodos GETTERS para acessar os atributos privados

    // Retorna o identificador do telefone
    public int getId() {return this.id;}
    // Retorna o número de telefone
    public String getNumero() {return this.numero;}
    // Retorna o identificador do transportador associado
    public String getId_transp() {return this.id_transp;}
    // Retorna o identificador do responsável associado
    public String getId_resp() {return this.id_resp;}

    // Métodos SETTERS para modificar os atributos privados

    // Define o número de telefone
    public void setNumero(String numero) {this.numero = numero;}

    // Método toString para representar a classe Telefone como uma String
    public String toString() {
        return "Telefone: " + "\nID: "+ this.id + "\nNumero: " + this.numero +
                "\nID_transp: " + this.id_transp + "\nID_resp: " + this.id_resp;
    }
}
