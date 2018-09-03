package com.weza_lab.benenfance.optimumcoops.pojo;

public class Deales {
    private int id_d;
    private String name_d;
    private String phone_d;

    public Deales() {
    }

    public Deales(int id_d, String name_d, String phone_d) {
        this.id_d = id_d;
        this.name_d = name_d;
        this.phone_d = phone_d;
    }

    public int getId_d() {
        return id_d;
    }

    public void setId_d(int id_d) {
        this.id_d = id_d;
    }

    public String getName_d() {
        return name_d;
    }

    public void setName_d(String name_d) {
        this.name_d = name_d;
    }

    public String getPhone_d() {
        return phone_d;
    }

    public void setPhone_d(String phone_d) {
        this.phone_d = phone_d;
    }
}
