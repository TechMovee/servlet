package org.example.projecttechmovee.ClasseTabelas;

public class Admin {
    private int id;
    private String name;
    private String password;

    //  Construtor
    public Admin(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    //    GETTERS
    public String getPassword() {
        return this.password;
    }
    public String getName() {
        return this.name;
    }
    public int getId() {
        return this.id;
    }

    //    SETTERS
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //    toString
    @Override
    public String toString() {
        return "\nAdmin: " + "\nId: " + this.id + "\nNome: " + this.name + "\nPassword: " + this.password;
    }
}
