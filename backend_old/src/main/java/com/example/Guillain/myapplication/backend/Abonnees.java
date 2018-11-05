package com.example.Guillain.myapplication.backend;

/**
 * Created by guillain on 08/10/2016.
 */

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index
public class Abonnees {

    @Id
    public Long id_a;
    public String login;
    public String password;
    public String compteur;
    public String photo;
    public String identite;
    public String sommes;

    public Abonnees() {
    }

    public Abonnees(String login) {
        this.login = login;
    }

    public Abonnees(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Abonnees(String login, String password, String compteur) {
        this.login = login;
        this.password = password;
        this.compteur = compteur;
    }

    public Abonnees(String login, String password, String compteur, String identite, String sommes) {

        this.login = login;
        this.password = password;
        this.compteur = compteur;
        //this.photo = photo;
        this.identite = identite;
        this.sommes = sommes;
    }

    public void constuct(String login, String password, String compteur, String identite, String sommes) {

        this.login = login;
        this.password = password;
        this.compteur = compteur;
        //this.photo = photo;
        this.identite = identite;
        this.sommes = sommes;
    }

    public Long getId_a() {
        return id_a;
    }

    public void setId_a(Long id_a) {
        this.id_a = id_a;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompteur() {
        return compteur;
    }

    public void setCompteur(String compteur) {
        this.compteur = compteur;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIdentite() {
        return identite;
    }

    public void setIdentite(String identite) {
        identite = identite;
    }

    public String getSommes() {
        return sommes;
    }

    public void setSommes(String sommes) {
        this.sommes = sommes;
    }


}
