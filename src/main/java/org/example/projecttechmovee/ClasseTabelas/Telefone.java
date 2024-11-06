package org.example.projecttechmovee.ClasseTabelas;

public class Telefone {

    // Atributos privados da classe Telefone
    private int id; // Identificador único do telefone
    private String numero; // Número do telefone
    private String idTransp; // Identificador do transportador associado (caso exista)
    private String idResp; // Identificador do responsável associado (caso exista)

    // Construtor que inicializa todos os atributos da classe Telefone
    public Telefone(int id, String numero, String id_resp, String id_transp){
        this.id = id;
        this.numero = numero;
        this.idResp = id_resp;
        this.idTransp = id_transp;
    }

    // Métodos GETTERS para acessar os atributos privados

    // Retorna o identificador do telefone
    public int getId() {return this.id;}
    // Retorna o número de telefone
    public String getNumero() {return this.numero;}
    // Retorna o identificador do transportador associado
    public String getId_transp() {return this.idTransp;}
    // Retorna o identificador do responsável associado
    public String getId_resp() {return this.idResp;}

    // Métodos SETTERS para modificar os atributos privados

    // Define o número de telefone
    public void setNumero(String numero) {this.numero = numero;}

    // Método toString para representar a classe Telefone como uma String
    public String toString() {
        return "Telefone: " + "\nID: "+ this.id + "\nNumero: " + this.numero +
                "\nID_transp: " + this.idTransp + "\nID_resp: " + this.idResp;
    }
}
