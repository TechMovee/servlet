package org.example.projecttechmovee.ClasseTabelas;

public class TransportadoresTelefone {
    private String transportadorCnh;
    private int telefoneId;

    //    Construtor
    public TransportadoresTelefone(String transportadorCnh, int telefoneId) {
        this.transportadorCnh = transportadorCnh;
        this.telefoneId = telefoneId;
    }

//    Getters

    public String getTransportadorCnh() {
        return this.transportadorCnh;
    }
    public int getTelefoneId() {
        return this.telefoneId;
    }

//    Setters

    public void setTransportadorCnh(String transportadorCnh) {
        this.transportadorCnh = transportadorCnh;
    }
    public void setTelefoneId(int telefoneId) {
        this.telefoneId = telefoneId;
    }

//    toString

    @Override
    public String toString() {
        return "\nTransportadorCnh: " + this.transportadorCnh + "\nTelefoneId: " + this.telefoneId;
    }
}
