package com.weza_lab.benenfance.optimumcoops.pojo;

public class Acheteurs {
    private int id_ac;
    private String name_ac;
    private String phone_ac;

    public Acheteurs() {
    }

    public Acheteurs(int id_ac, String name_ac, String phone_ac) {
        this.id_ac = id_ac;
        this.name_ac = name_ac;
        this.phone_ac = phone_ac;
    }

    public int getId_ac() {
        return id_ac;
    }

    public void setId_ac(int id_ac) {
        this.id_ac = id_ac;
    }

    public String getName_ac() {
        return name_ac;
    }

    public void setName_ac(String name_ac) {
        this.name_ac = name_ac;
    }

    public String getPhone_ac() {
        return phone_ac;
    }

    public void setPhone_ac(String phone_ac) {
        this.phone_ac = phone_ac;
    }
}
