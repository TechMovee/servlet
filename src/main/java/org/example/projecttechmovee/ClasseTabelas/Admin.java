package org.example.projecttechmovee.ClasseTabelas;

public class Admin {
    private int id;
    private String name;
    private String email;
    private String senha;

    //  Construtor
    public Admin(int id, String name,String email, String senha) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.senha = senha;
    }

    public Admin(String name,String email, String senha) {
        this.id = -1;
        this.name = name;
        this.email = email;
        this.senha = senha;
    }

    public Admin() {
    }

    //    GETTERS
    public String getSenha() {
        return this.senha;
    }
    public String getName() {
        return this.name;
    }
    public int getId() {
        return this.id;
    }
    public String getEmail() {return email;}

    //    SETTERS
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setEmail(String email) {this.email = email;}


    //    toString

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + this.id +
                ", nome='" + this.name + '\'' +
                ", email='" + this.email + '\'' +
                '}';
    }
}
