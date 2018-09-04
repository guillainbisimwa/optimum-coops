package com.weza_lab.benenfance.optimumcoops.pojo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Credit_block {
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

    public Credit_block() {
    }

    public Credit_block(int id_credit, long timestamp, String previous_hash, float somme_credit, String phone_user_credit, int type_user_credit, int etat_credit, float taux_credit, String motif_credit) {
        this.id_credit = id_credit;
        this.timestamp = timestamp;
        hash = Credit_block.calculateHash(this);
        this.previous_hash = previous_hash;
        nonce = 0;
        this.somme_credit = somme_credit;
        this.phone_user_credit = phone_user_credit;
        this.type_user_credit = type_user_credit;
        this.etat_credit = etat_credit;
        this.taux_credit = taux_credit;
        this.motif_credit = motif_credit;
    }

    public static String calculateHash(Credit_block credit_block) {
        if (credit_block != null) {
            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                return null;
            }

            String text = credit_block.str();
            final byte bytes[] = digest.digest(text.getBytes());
            final StringBuilder builder = new StringBuilder();

            for (final byte b : bytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    builder.append(0);
                }
                builder.append(hex);
            }
            return builder.toString();
        }
        return null;
    }

    public int getId_credit() {
        return id_credit;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getHash() {
        return hash;
    }

    public String getPrevious_hash() {
        return previous_hash;
    }

    public int getNonce() {
        return nonce;
    }

    public float getSomme_credit() {
        return somme_credit;
    }

    public String getPhone_user_credit() {
        return phone_user_credit;
    }

    public int getType_user_credit() {
        return type_user_credit;
    }

    public int getEtat_credit() {
        return etat_credit;
    }

    public float getTaux_credit() {
        return taux_credit;
    }

    public String getMotif_credit() {
        return motif_credit;
    }

    public String str() {
        return id_credit + timestamp + previous_hash + somme_credit + taux_credit + phone_user_credit + etat_credit + nonce;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Credit_block #").append(id_credit).append(" [previous:hash: ").append(previous_hash).append(",")
                .append(" Timestamp: ").append(new Date(timestamp)).append(", ").append(" Phone: ").append(phone_user_credit)
                .append("  somme ").append(somme_credit).append(" Taux: ").append(taux_credit).append(" Hash: ").append(hash);
        return builder.toString();
    }

    public void mineCreditBlock(int difficulty) {
        nonce = 0;
        // while (!getHash().substring(0,difficulty).equals(Utils.zeros(difficulty)))
        while (!getHash().substring(0, difficulty).equals(difficulty)) {
            nonce++;
            hash = Credit_block.calculateHash(this);
        }
    }
}














