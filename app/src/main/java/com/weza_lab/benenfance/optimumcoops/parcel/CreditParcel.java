package com.weza_lab.benenfance.optimumcoops.parcel;

import android.os.Parcel;
import android.os.Parcelable;

public class CreditParcel implements Parcelable {
    public static final Parcelable.Creator<CreditParcel> CREATOR = new Parcelable.Creator<CreditParcel>() {
        @Override
        public CreditParcel createFromParcel(Parcel parcel) {
            return new CreditParcel(parcel);
        }

        @Override
        public CreditParcel[] newArray(int i) {
            return new CreditParcel[0];
        }
    };

    private int id_credit;
    private long timestamp; //date
    private String hash;
    private String previous_hash;
    private int nonce;

    private float somme_credit;
    private String phone_user_credit;
    private int type_user_credit;
    private int etat_credit;
    private float taux_credit;
    private String motif_credit;
    private int duree;

    public CreditParcel() {
    }

    public CreditParcel(int id_credit, long timestamp, String hash, String previous_hash, int nonce, float somme_credit,
                        String phone_user_credit, int type_user_credit, int etat_credit, float taux_credit, String motif_credit, int duree) {
        this.id_credit = id_credit;
        this.timestamp = timestamp;
        this.hash = hash;
        this.previous_hash = previous_hash;
        this.nonce = nonce;
        this.somme_credit = somme_credit;
        this.phone_user_credit = phone_user_credit;
        this.type_user_credit = type_user_credit;
        this.etat_credit = etat_credit;
        this.taux_credit = taux_credit;
        this.motif_credit = motif_credit;
        this.duree = duree;
    }

    public CreditParcel(Parcel data) {
        this.id_credit = data.readInt();
        this.timestamp = data.readLong();
        this.hash = data.readString();
        this.previous_hash = data.readString();
        this.nonce = data.readInt();
        this.somme_credit = data.readFloat();
        this.phone_user_credit = data.readString();
        this.type_user_credit = data.readInt();
        this.etat_credit = data.readInt();
        this.taux_credit = data.readFloat();
        this.motif_credit = data.readString();
        this.duree = data.readInt();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id_credit);
        parcel.writeLong(timestamp);
        parcel.writeString(hash);
        parcel.writeString(previous_hash);
        parcel.writeInt(nonce);
        parcel.writeFloat(somme_credit);
        parcel.writeString(phone_user_credit);
        parcel.writeInt(type_user_credit);
        parcel.writeInt(etat_credit);
        parcel.writeFloat(taux_credit);
        parcel.writeString(motif_credit);
        parcel.writeInt(duree);
    }

    public int getId_credit() {
        return id_credit;
    }

    public void setId_credit(int id_credit) {
        this.id_credit = id_credit;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPrevious_hash() {
        return previous_hash;
    }

    public void setPrevious_hash(String previous_hash) {
        this.previous_hash = previous_hash;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public float getSomme_credit() {
        return somme_credit;
    }

    public void setSomme_credit(float somme_credit) {
        this.somme_credit = somme_credit;
    }

    public String getPhone_user_credit() {
        return phone_user_credit;
    }

    public void setPhone_user_credit(String phone_user_credit) {
        this.phone_user_credit = phone_user_credit;
    }

    public int getType_user_credit() {
        return type_user_credit;
    }

    public void setType_user_credit(int type_user_credit) {
        this.type_user_credit = type_user_credit;
    }

    public int getEtat_credit() {
        return etat_credit;
    }

    public void setEtat_credit(int etat_credit) {
        this.etat_credit = etat_credit;
    }

    public float getTaux_credit() {
        return taux_credit;
    }

    public void setTaux_credit(float taux_credit) {
        this.taux_credit = taux_credit;
    }

    public String getMotif_credit() {
        return motif_credit;
    }

    public void setMotif_credit(String motif_credit) {
        this.motif_credit = motif_credit;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
}


