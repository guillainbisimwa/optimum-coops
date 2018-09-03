package com.weza_lab.benenfance.optimumcoops.agri.sync;


import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.weza_lab.benenfance.optimumcoops.database.DBHelper;
import com.weza_lab.benenfance.optimumcoops.database.DBQueries;
import com.weza_lab.benenfance.optimumcoops.pojo.Agriculteurs;

public class Add_agriculteur extends IntentService {
    DBQueries dbQueries;
    DBHelper dbHelper;

    public Add_agriculteur() {
        super("");
    }

    public Add_agriculteur(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        dbQueries = new DBQueries(getApplicationContext());
        dbHelper = new DBHelper(getApplicationContext());
        if (intent != null) {
            //declare et initialise les varaibles
            String nom_ = null;
            String postnom_ = null;
            String phone_ = null;
            boolean gender = true;
            String mots_de_passe_ = null;
            String mots_de_passe_conf_ = null;
            String adresse_ = null;
            String plantation_ = null;

            //check si intent has extra

            if (intent.hasExtra("nom")) {
                nom_ = intent.getStringExtra("nom");
            }
            if (intent.hasExtra("postnom")) {
                postnom_ = intent.getStringExtra("postnom");
            }
            if (intent.hasExtra("phone")) {
                phone_ = intent.getStringExtra("phone");
            }
            if (intent.hasExtra("mots_de_passe")) {
                mots_de_passe_ = intent.getStringExtra("mots_de_passe");
            }
            if (intent.hasExtra("mots_de_passe_conf")) {
                mots_de_passe_conf_ = intent.getStringExtra("mots_de_passe_conf");
            }
            if (intent.hasExtra("adresse")) {
                adresse_ = intent.getStringExtra("adresse");
            }
            if (intent.hasExtra("plantation")) {
                plantation_ = intent.getStringExtra("plantation");
            }

            dbQueries.open();

            Agriculteurs agriculteurs = new Agriculteurs(0, nom_, phone_, postnom_, gender ? "M" : "F", mots_de_passe_, mots_de_passe_conf_,
                    adresse_, 0, 0, 0, null, 0, 100, 0, plantation_);
            if (!dbQueries.checkAgriculteurs(phone_)) {
                dbQueries.insertAgriculteur(agriculteurs);
            } else
                Toast.makeText(getApplicationContext(), "Ce numero existe deja", Toast.LENGTH_LONG).show();


            dbQueries.close();

            //enregistrer ds la base des donnees SQLITE
            /*ContentValues contentValues = new ContentValues();
            contentValues.put(OcContract.Agriculteurs.COLUMN_NAME, nom_);
            contentValues.put(OcContract.Agriculteurs.COLUMN_LAST_NAME, postnom_);
            contentValues.put(OcContract.Agriculteurs.COLUMN_GENDER, gender ? "male" : "female");
            contentValues.put(OcContract.Agriculteurs.COLUMN_PHONE, phone_);
            contentValues.put(OcContract.Agriculteurs.COLUMN_PASSWORD, mots_de_passe_);
            contentValues.put(OcContract.Agriculteurs.COLUMN_PASSWORD_CONFIRM, mots_de_passe_conf_);
            contentValues.put(OcContract.Agriculteurs.COLUMN_ADDRESS, adresse_);
            contentValues.put(OcContract.Agriculteurs.COLUMN_PLANTATION, plantation_);

            contentValues.put(OcContract.Agriculteurs.COLUMN_IS_CHIEF_GROUP, 0);
            contentValues.put(OcContract.Agriculteurs.COLUMN_IS_SYNC, 0);
            contentValues.put(OcContract.Agriculteurs.COLUMN_IS_VALIDATE, 0);
            contentValues.put(OcContract.Agriculteurs.COLUMN_IS_UPDATE, 0);
*/
            /*ContentValues contentValues_user = new ContentValues();
            contentValues_user.put(OcContract.Users.COLUMN_USER_NAME, nom_);

            getContentResolver().insert(OcContract.Users.CONTENT_URI, contentValues_user);
*/


            //Toast.makeText(getApplicationContext(),"Nom "+nom_,Toast.LENGTH_SHORT).show();

            //getContentResolver().insert(OcContract.Agriculteurs.CONTENT_URI, contentValues);


        }
    }
}
