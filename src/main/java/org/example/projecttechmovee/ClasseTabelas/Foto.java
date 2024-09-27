package org.example.projecttechmovee.ClasseTabelas;

public class Foto {
    private int id;
    private String url;

    //    CONSTRUTOR
    public Foto(int id, String url) {
        this.id = id;
        this.url = url;
    }

    //    GETTER
    public int getId() {
        return this.id;
    }
    public String getUrl() {
        return this.url;
    }

    //    SETTER
    public void setUrl(String url) {
        this.url = url;
    }

    //    toString
    @Override
    public String toString() {
        return "\nFoto: " + "\nId:" + this.id + "\nURL: " + this.url;
    }
}
