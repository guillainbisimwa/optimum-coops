package com.weza_lab.benenfance.optimumcoops.parcel;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonnesParcel implements Parcelable {
    public static final Creator<PersonnesParcel> CREATOR = new Creator<PersonnesParcel>() {
        @Override
        public PersonnesParcel createFromParcel(Parcel parcel) {
            return new PersonnesParcel(parcel);
        }

        @Override
        public PersonnesParcel[] newArray(int i) {
            return new PersonnesParcel[0];
        }
    };

    private int id_a;
    private String nom_a;
    private String phone_a;

    private String postnom_a;
    private String gender_a;
    private String mots_de_passe_a;
    private String mots_de_passe_conf_a;
    private String adresse_a;

    private int is_sync_a;
    private int is_validate_a;
    private int is_update_a;

    //private byte[] contactPhoto;
    private int id_group;
    private int default_type;
    private int is_chef_group;

    public PersonnesParcel() {
    }

    public PersonnesParcel(int id_a, String nom_a, String phone_a, String postnom_a, String gender_a, String mots_de_passe_a,
                           String mots_de_passe_conf_a, String adresse_a, int is_sync_a, int is_validate_a, int is_update_a,
                           int id_group, int default_type, int is_chef_group) {
        this.id_a = id_a;
        this.nom_a = nom_a;
        this.phone_a = phone_a;
        this.postnom_a = postnom_a;
        this.gender_a = gender_a;
        this.mots_de_passe_a = mots_de_passe_a;
        this.mots_de_passe_conf_a = mots_de_passe_conf_a;
        this.adresse_a = adresse_a;
        this.is_sync_a = is_sync_a;
        this.is_validate_a = is_validate_a;
        this.is_update_a = is_update_a;
        //this.contactPhoto = contactPhoto;
        this.id_group = id_group;
        this.default_type = default_type;
        this.is_chef_group = is_chef_group;
    }

    public PersonnesParcel(Parcel parcel) {
        this.id_a = parcel.readInt();
        this.nom_a = parcel.readString();
        this.phone_a = parcel.readString();
        this.postnom_a = parcel.readString();
        this.gender_a = parcel.readString();
        this.mots_de_passe_a = parcel.readString();
        this.mots_de_passe_conf_a = parcel.readString();
        this.adresse_a = parcel.readString();
        this.is_sync_a = parcel.readInt();
        this.is_validate_a = parcel.readInt();
        this.is_update_a = parcel.readInt();
        //this.contactPhoto = parcel.readByte();
        this.id_group = parcel.readInt();
        this.default_type = parcel.readInt();
        this.is_chef_group = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id_a);
        parcel.writeString(nom_a);
        parcel.writeString(phone_a);
        parcel.writeString(postnom_a);
        parcel.writeString(gender_a);
        parcel.writeString(mots_de_passe_a);
        parcel.writeString(mots_de_passe_conf_a);
        parcel.writeString(adresse_a);
        parcel.writeInt(is_sync_a);
        parcel.writeInt(is_validate_a);
        parcel.writeInt(is_update_a);
        parcel.writeInt(id_group);
        parcel.writeInt(default_type);
        parcel.writeInt(is_chef_group);
    }

    public int getId_a() {
        return id_a;
    }

    public void setId_a(int id_a) {
        this.id_a = id_a;
    }

    public String getNom_a() {
        return nom_a;
    }

    public void setNom_a(String nom_a) {
        this.nom_a = nom_a;
    }

    public String getPhone_a() {
        return phone_a;
    }

    public void setPhone_a(String phone_a) {
        this.phone_a = phone_a;
    }

    public String getPostnom_a() {
        return postnom_a;
    }

    public void setPostnom_a(String postnom_a) {
        this.postnom_a = postnom_a;
    }

    public String getGender_a() {
        return gender_a;
    }

    public void setGender_a(String gender_a) {
        this.gender_a = gender_a;
    }

    public String getMots_de_passe_a() {
        return mots_de_passe_a;
    }

    public void setMots_de_passe_a(String mots_de_passe_a) {
        this.mots_de_passe_a = mots_de_passe_a;
    }

    public String getMots_de_passe_conf_a() {
        return mots_de_passe_conf_a;
    }

    public void setMots_de_passe_conf_a(String mots_de_passe_conf_a) {
        this.mots_de_passe_conf_a = mots_de_passe_conf_a;
    }

    public String getAdresse_a() {
        return adresse_a;
    }

    public void setAdresse_a(String adresse_a) {
        this.adresse_a = adresse_a;
    }

    public int getIs_sync_a() {
        return is_sync_a;
    }

    public void setIs_sync_a(int is_sync_a) {
        this.is_sync_a = is_sync_a;
    }

    public int getIs_validate_a() {
        return is_validate_a;
    }

    public void setIs_validate_a(int is_validate_a) {
        this.is_validate_a = is_validate_a;
    }

    public int getIs_update_a() {
        return is_update_a;
    }

    public void setIs_update_a(int is_update_a) {
        this.is_update_a = is_update_a;
    }

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public int getDefault_type() {
        return default_type;
    }

    public void setDefault_type(int default_type) {
        this.default_type = default_type;
    }

    public int getIs_chef_group() {
        return is_chef_group;
    }

    public void setIs_chef_group(int is_chef_group) {
        this.is_chef_group = is_chef_group;
    }
}
