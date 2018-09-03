package com.weza_lab.benenfance.optimumcoops.pojo;

public class Groupes {
    private int id_g;
    private String name_g;
    private String adresse_g;

    public Groupes() {
    }

    public Groupes(int id_g, String name_g, String adresse_g) {
        this.id_g = id_g;
        this.name_g = name_g;
        this.adresse_g = adresse_g;
    }

    public int getId_g() {
        return id_g;
    }

    public void setId_g(int id_g) {
        this.id_g = id_g;
    }

    public String getName_g() {
        return name_g;
    }

    public void setName_g(String name_g) {
        this.name_g = name_g;
    }

    public String getAdresse_g() {
        return adresse_g;
    }

    public void setAdresse_g(String adresse_g) {
        this.adresse_g = adresse_g;
    }
}
