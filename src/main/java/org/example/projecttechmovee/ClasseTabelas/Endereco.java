package org.example.projecttechmovee.ClasseTabelas;

public class Endereco {
    private int id;
    private String cep;
    private String bairro;
    private String rua;
    private String numero;

//    CONSTRUTOR

    public Endereco(int id, String cep, String bairro, String rua, String numero) {
        this.id = id;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

//    GETTER

    public int getId() {
        return this.id;
    }
    public String getCep() {
        return this.cep;
    }
    public String getBairro() {
        return this.bairro;
    }
    public String getRua() {
        return this.rua;
    }
    public String getNumero() {
        return this.numero;
    }

//    SETTER

    public void setId(int id) {
        this.id = id;
    }
    public void setCep(String cep) {this.cep = cep;}
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

//    toString

    @Override
    public String toString() {
        return "\nEndereco: " + "\nid: " + this.id + "\ncep:" + this.cep  + "\nBairro:" + this.bairro +
                "\nRua=: " + this.rua  + "\nNumero:" + this.numero;
    }
}
