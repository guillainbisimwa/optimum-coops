package com.weza_lab.benenfance.optimumcoops.pojo;

public class Groups {
    private int id_group;
    private String name_group;
    private String adress_group;
    private byte[] photo_profile_group;
    private int type_group;
    /***
     * NOT ALOWED IN DB
     * */
    private int nbr_users;

    public Groups() {
    }

    public Groups(int id_group, String name_group, String adress_group, byte[] photo_profile_group, int type_group) {
        this.id_group = id_group;
        this.name_group = name_group;
        this.adress_group = adress_group;
        this.photo_profile_group = photo_profile_group;
        this.type_group = type_group;
    }

    public Groups(int id_group, String name_group, String adress_group, byte[] photo_profile_group, int type_group, int nbr_users) {
        this.id_group = id_group;
        this.name_group = name_group;
        this.adress_group = adress_group;
        this.photo_profile_group = photo_profile_group;
        this.type_group = type_group;
        this.nbr_users = nbr_users;
    }

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public String getName_group() {
        return name_group;
    }

    public void setName_group(String name_group) {
        this.name_group = name_group;
    }

    public String getAdress_group() {
        return adress_group;
    }

    public void setAdress_group(String adress_group) {
        this.adress_group = adress_group;
    }

    public byte[] getPhoto_profile_group() {
        return photo_profile_group;
    }

    public void setPhoto_profile_group(byte[] photo_profile_group) {
        this.photo_profile_group = photo_profile_group;
    }

    public int getType_group() {
        return type_group;
    }

    public void setType_group(int type_group) {
        this.type_group = type_group;
    }

    public int getNbr_users() {
        return nbr_users;
    }

    public void setNbr_users(int nbr_users) {
        this.nbr_users = nbr_users;
    }
}
