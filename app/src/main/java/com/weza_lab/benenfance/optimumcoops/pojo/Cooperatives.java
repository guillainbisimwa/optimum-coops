package com.weza_lab.benenfance.optimumcoops.pojo;

public class Cooperatives {
    private int id_c;
    private String name_c;
    private String phone_c;

    public Cooperatives() {
    }

    public Cooperatives(int id_c, String name_c, String phone_c) {
        this.id_c = id_c;
        this.name_c = name_c;
        this.phone_c = phone_c;
    }

    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public String getName_c() {
        return name_c;
    }

    public void setName_c(String name_c) {
        this.name_c = name_c;
    }

    public String getPhone_c() {
        return phone_c;
    }

    public void setPhone_c(String phone_c) {
        this.phone_c = phone_c;
    }
}
