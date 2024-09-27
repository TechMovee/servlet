package org.example.projecttechmovee.ClasseTabelas;

public class ResponsavelTelefone {
    private String responsavelCpf;
    private int telefoneId;

    //    Construtor
    public ResponsavelTelefone(String responsavelCpf, int telefoneId) {
        this.responsavelCpf = responsavelCpf;
        this.telefoneId = telefoneId;
    }

    //    GETTER
    public String getResponsavelCpf() {
        return this.responsavelCpf;
    }
    public int getTelefoneId() {
        return this.telefoneId;
    }

//    SETTER

    public void setResponsavelCpf(String responsavelCpf) {
        this.responsavelCpf = responsavelCpf;
    }
    public void setTelefoneId(int telefoneId) {
        this.telefoneId = telefoneId;
    }

//    toString

    @Override
    public String toString() {
        return "\nResponsavelTelefone: " + "\nResponsavelCpf: " + this.responsavelCpf + "\nTelefoneId: " + this.telefoneId;
    }
}
