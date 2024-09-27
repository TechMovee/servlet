package org.example.projecttechmovee.ClasseTabelas;

public class Vans {

    private String placa;
    private String modelo;
    private int ano;
    private int foto_id;
    private String transportador_cnh;

//    Construtor

    public Vans(String placa, String modelo, int ano, int foto_id, String transportador_cnh) {
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.foto_id = foto_id;
        this.transportador_cnh = transportador_cnh;
    }

//    Getters

    public String getPlaca() {return this.placa;}
    public String getModelo() {return this.modelo;}
    public int getAno() {return this.ano;}
    public int getFoto_id() {return this.foto_id;}
    public String getTransportador_cnh() {return this.transportador_cnh;}

//    Setters

    public void setPlaca(String placa) {this.placa = placa;}
    public void setModelo(String modelo) {this.modelo = modelo;}
    public void setAno(int ano) {this.ano = ano;}
    public void setFoto_id(int foto_id) {this.foto_id = foto_id;}
    public void setTransportador_cnh(String transportador_cnh) {this.transportador_cnh = transportador_cnh;}

//    toString

    @Override
    public String toString() {
        return "\nVans: " + "\nPlaca: " + this.placa + "\nModelo: " + this.modelo  +
                "\nAno: " + this.ano + "\nFoto_id: " + this.foto_id +
                "\nTransportador_cnh: " + this.transportador_cnh;
    }
}
