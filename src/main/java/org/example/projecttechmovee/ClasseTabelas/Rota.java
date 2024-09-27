package org.example.projecttechmovee.ClasseTabelas;

public class Rota {
    private int id;
    private String nome;
    private String periodo;
    private int qntdParadas;
    private String tempo;
    private int enderecoId;

//    Construtor

    public Rota(int id, String nome, String periodo, int qntdParadas, String tempo, int enderecoId) {
        this.id = id;
        this.nome = nome;
        this.periodo = periodo;
        this.qntdParadas = qntdParadas;
        this.tempo = tempo;
        this.enderecoId = enderecoId;
    }

//    Getter

    public int getId() {
        return this.id;
    }
    public String getNome() {
        return this.nome;
    }
    public String getPeriodo() {
        return this.periodo;
    }
    public int getQntdParadas() {
        return this.qntdParadas;
    }
    public String getTempo() {
        return this.tempo;
    }
    public int getEnderecoId() {
        return this.enderecoId;
    }

//    Setter

    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    public void setQntdParadas(int qntdParadas) {
        this.qntdParadas = qntdParadas;
    }
    public void setTempo(String tempo) {
        this.tempo = tempo;
    }
    public void setEnderecoId(int enderecoId) {
        this.enderecoId = enderecoId;
    }

//    toString

    @Override
    public String toString() {
        return "\nID: " + this.id + "\nNome: " + this.nome + "\nPeríodo: " + this.periodo +
                "\nQntdParadas: " + this.qntdParadas + "\nTempo: " + this.tempo + "\nEnderecoId: " + this.enderecoId;
    }
}
