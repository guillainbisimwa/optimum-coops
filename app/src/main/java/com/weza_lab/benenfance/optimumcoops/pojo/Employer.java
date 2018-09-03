package com.weza_lab.benenfance.optimumcoops.pojo;

public class Employer extends Personnes {
    private String employeur;

    public Employer() {
        super();
    }

    public Employer(int id_a, String nom_a, String phone_a, String postnom_a, String gender_a, String mots_de_passe_a, String mots_de_passe_conf_a, String adresse_a, int is_sync_a, int is_validate_a, int is_update_a, byte[] contactPhoto, int id_group, int default_type, int is_chef_group) {
        super(id_a, nom_a, phone_a, postnom_a, gender_a, mots_de_passe_a, mots_de_passe_conf_a, adresse_a, is_sync_a, is_validate_a, is_update_a, contactPhoto, id_group, default_type, is_chef_group);
    }

    public Employer(int id_a, String nom_a, String phone_a, String postnom_a, String gender_a, String mots_de_passe_a, String mots_de_passe_conf_a, String adresse_a, int is_sync_a, int is_validate_a, int is_update_a, byte[] contactPhoto, int id_group, int default_type, int is_chef_group, String employeur) {
        super(id_a, nom_a, phone_a, postnom_a, gender_a, mots_de_passe_a, mots_de_passe_conf_a, adresse_a, is_sync_a, is_validate_a, is_update_a, contactPhoto, id_group, default_type, is_chef_group);
        this.employeur = employeur;
    }

    public String getEmployeur() {
        return employeur;
    }

    public void setEmployeur(String employeur) {
        this.employeur = employeur;
    }
}
