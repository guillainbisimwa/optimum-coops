package com.weza_lab.benenfance.optimumcoops.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.weza_lab.benenfance.optimumcoops.pojo.Agriculteurs;
import com.weza_lab.benenfance.optimumcoops.pojo.Employer;
import com.weza_lab.benenfance.optimumcoops.pojo.Entrepreneurs;
import com.weza_lab.benenfance.optimumcoops.pojo.Personnes;
import com.weza_lab.benenfance.optimumcoops.pojo.Petit_commercant;
import com.weza_lab.benenfance.optimumcoops.pojo.Users;

import java.util.ArrayList;

public class DBQueries {

    private Context context;
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public DBQueries(Context context) {
        this.context = context;
    }

    public DBQueries open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // Users
    public boolean insertUser(Users users) {
        ContentValues values = new ContentValues();
        values.put(DBConstants.CONTACT_PERSON_NAME, users.getContactPersonName());
        values.put(DBConstants.CONTACT_NO, users.getContactNumber());
        values.put(DBConstants.CONTACT_PHOTO, users.getContactPhoto());
        return database.insert(DBConstants.USER_TABLE, null, values) > -1;
    }

    /**
     * Agriculteurs
     **/

    public boolean updateAgriculteur(Agriculteurs agriculteurs, String mPhone) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.COLUMN_NAME_AGRI, agriculteurs.getNom_a());
        contentValues.put(DBConstants.COLUMN_LAST_NAME_AGRI, agriculteurs.getPostnom_a());
        contentValues.put(DBConstants.COLUMN_GENDER_AGRI, agriculteurs.getGender_a());
        contentValues.put(DBConstants.COLUMN_PHONE_AGRI, agriculteurs.getPhone_a());
        contentValues.put(DBConstants.COLUMN_PASSWORD_AGRI, agriculteurs.getMots_de_passe_a());
        contentValues.put(DBConstants.COLUMN_PASSWORD_CONFIRM_AGRI, agriculteurs.getMots_de_passe_conf_a());
        contentValues.put(DBConstants.COLUMN_ADDRESS_AGRI, agriculteurs.getAdresse_a());
        contentValues.put(DBConstants.COLUMN_PLANTATION_AGRI, agriculteurs.getPlantation_a());

        contentValues.put(DBConstants.CONTACT_PHOTO_AGRI, agriculteurs.getContactPhoto());

        contentValues.put(DBConstants.COLUMN_IS_CHIEF_GROUP_AGRI, agriculteurs.getIs_chef_group());
        contentValues.put(DBConstants.COLUMN_IS_SYNC_AGRI, agriculteurs.getIs_sync_a());
        contentValues.put(DBConstants.COLUMN_IS_VALIDATE_AGRI, agriculteurs.getIs_validate_a());
        contentValues.put(DBConstants.COLUMN_IS_UPDATE_AGRI, agriculteurs.getIs_update_a());

        return database.update(DBConstants.TABLE_NAME_AGRICULTEURS, contentValues, DBConstants.COLUMN_PHONE_AGRI + " = ? ", new String[]
                {String.valueOf(mPhone)}) > -1;
        //database.execSQL("DELETE FROM " + TABLE_PRODUITS + " WHERE " + COLUMN_PRODUCTNAME + " = ' " + productName + " ';");
    }

    public boolean deleteAgriculteur(String mPhone) {
        return database.delete(DBConstants.TABLE_NAME_AGRICULTEURS, DBConstants.COLUMN_PHONE_AGRI + " = ? ", new String[]
                {String.valueOf(mPhone)}) > -1;
    }

    // Agriculteurs
    public boolean insertAgriculteur(Agriculteurs agriculteurs) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.COLUMN_NAME_AGRI, agriculteurs.getNom_a());
        contentValues.put(DBConstants.COLUMN_LAST_NAME_AGRI, agriculteurs.getPostnom_a());
        contentValues.put(DBConstants.COLUMN_GENDER_AGRI, agriculteurs.getGender_a());
        contentValues.put(DBConstants.COLUMN_PHONE_AGRI, agriculteurs.getPhone_a());
        contentValues.put(DBConstants.COLUMN_PASSWORD_AGRI, agriculteurs.getMots_de_passe_a());
        contentValues.put(DBConstants.COLUMN_PASSWORD_CONFIRM_AGRI, agriculteurs.getMots_de_passe_conf_a());
        contentValues.put(DBConstants.COLUMN_ADDRESS_AGRI, agriculteurs.getAdresse_a());
        contentValues.put(DBConstants.COLUMN_PLANTATION_AGRI, agriculteurs.getPlantation_a());

        contentValues.put(DBConstants.CONTACT_PHOTO_AGRI, agriculteurs.getContactPhoto());

        contentValues.put(DBConstants.COLUMN_IS_CHIEF_GROUP_AGRI, agriculteurs.getIs_chef_group());
        contentValues.put(DBConstants.COLUMN_IS_SYNC_AGRI, agriculteurs.getIs_sync_a());
        contentValues.put(DBConstants.COLUMN_IS_VALIDATE_AGRI, agriculteurs.getIs_validate_a());
        contentValues.put(DBConstants.COLUMN_IS_UPDATE_AGRI, agriculteurs.getIs_update_a());
        contentValues.put(DBConstants.COLUMN_ID_GROUP_AGRI, agriculteurs.getId_group());


        return database.insert(DBConstants.TABLE_NAME_AGRICULTEURS, null, contentValues) > -1;
    }

    public ArrayList<Users> readUsers() {
        ArrayList<Users> list = new ArrayList<>();
        try {
            Cursor cursor;
            database = dbHelper.getReadableDatabase();
            cursor = database.rawQuery(DBConstants.SELECT_QUERY, null);
            list.clear();
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        String contactId = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_ID));
                        String conPerson = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_PERSON_NAME));
                        String conNo = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_NO));
                        byte[] conPhoto = cursor.getBlob(cursor.getColumnIndex(DBConstants.CONTACT_PHOTO));
                        Users users = new Users(contactId, conPerson, conNo, conPhoto);
                        list.add(users);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
        } catch (Exception e) {
            Log.v("Exception", e.getMessage());
        }
        return list;
    }

    public Cursor listAgriCursor() {
        Cursor cursor;
        database = dbHelper.getReadableDatabase();
        cursor = database.rawQuery(DBConstants.SELECT_AGRICULTEUR_QUERY, null);
        return cursor;
    }

    public ArrayList<Agriculteurs> readAgriculteurs() {
        ArrayList<Agriculteurs> list = new ArrayList<>();
        try {
            Cursor cursor;
            database = dbHelper.getReadableDatabase();
            cursor = database.rawQuery(DBConstants.SELECT_AGRICULTEUR_QUERY, null);
            list.clear();
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        int id_a = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_ID_AGRI));
                        String nom_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_NAME_AGRI));
                        String phone_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_PHONE_AGRI));
                        String postnom_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_LAST_NAME_AGRI));
                        String gender_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_GENDER_AGRI));
                        String mots_de_passe_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_PASSWORD_AGRI));
                        String mots_de_passe_conf_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_PASSWORD_CONFIRM_AGRI));
                        String adresse_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_ADDRESS_AGRI));
                        String plantation_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_PLANTATION_AGRI));

                        byte[] conPhoto = cursor.getBlob(cursor.getColumnIndex(DBConstants.CONTACT_PHOTO_AGRI));

                        int is_sync_a = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_IS_SYNC_AGRI));
                        int is_validate_a = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_IS_VALIDATE_AGRI));
                        int is_update_a = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_IS_UPDATE_AGRI));
                        int is_chief_group = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_IS_CHIEF_GROUP_AGRI));
                        int id_group = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_ID_GROUP_AGRI));
                        int dafault_type = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_DEFAULT_TYPE_AGRI));

                        Agriculteurs agriculteurs = new Agriculteurs(id_a, nom_a, phone_a, postnom_a, gender_a, mots_de_passe_a,
                                mots_de_passe_conf_a, adresse_a, is_sync_a, is_validate_a, is_update_a,
                                conPhoto, id_group, dafault_type, is_chief_group, plantation_a);
                        list.add(agriculteurs);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
        } catch (Exception e) {
            Log.v("Exception", e.getMessage());
        }
        return list;
    }

    public Agriculteurs readOneAgriculteurs(String phone) {
        Agriculteurs a = new Agriculteurs();
        try {
            Cursor cursor;
            database = dbHelper.getReadableDatabase();
            cursor = database.rawQuery(DBConstants.SELECT_AGRICULTEUR_QUERY, null);

            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        int id_a = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_ID_AGRI));
                        String nom_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_NAME_AGRI));
                        String phone_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_PHONE_AGRI));
                        String postnom_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_LAST_NAME_AGRI));
                        String gender_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_GENDER_AGRI));
                        String mots_de_passe_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_PASSWORD_AGRI));
                        String mots_de_passe_conf_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_PASSWORD_CONFIRM_AGRI));
                        String adresse_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_ADDRESS_AGRI));
                        String plantation_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_PLANTATION_AGRI));

                        byte[] conPhoto = cursor.getBlob(cursor.getColumnIndex(DBConstants.CONTACT_PHOTO_AGRI));

                        int is_sync_a = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_IS_SYNC_AGRI));
                        int is_validate_a = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_IS_VALIDATE_AGRI));
                        int is_update_a = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_IS_UPDATE_AGRI));
                        int is_chief_group = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_IS_CHIEF_GROUP_AGRI));
                        int id_group = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_ID_GROUP_AGRI));
                        int dafault_type = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_DEFAULT_TYPE_AGRI));

                        if (phone.equals(phone_a)) {
                            Agriculteurs agriculteurs = new Agriculteurs(id_a, nom_a, phone_a, postnom_a, gender_a, mots_de_passe_a,
                                    mots_de_passe_conf_a, adresse_a, is_sync_a, is_validate_a, is_update_a,
                                    conPhoto, id_group, dafault_type, is_chief_group, plantation_a);
                            a = agriculteurs;
                            break;
                        }
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
        } catch (Exception e) {
            Log.v("Exception", e.getMessage());
        }
        return a;
    }

    public boolean checkAgriculteurs(String phone) {
        boolean return_ = false;
        try {
            Cursor cursor;
            database = dbHelper.getReadableDatabase();
            cursor = database.rawQuery(DBConstants.SELECT_AGRICULTEUR_QUERY, null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        String phone_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_PHONE_AGRI));
                        if (phone.equals(phone_a)) {
                            return_ = true;
                            break;
                        }

                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
        } catch (Exception e) {
            Log.v("Exception", e.getMessage());
            return return_;
        }
        return return_;
    }

    /**
     * * Petit commercants
     **/
    public boolean insertPetit_com(Petit_commercant petit_commercant) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.COLUMN_NAME_PETI_COM, petit_commercant.getNom_a());
        contentValues.put(DBConstants.COLUMN_LAST_NAME_PETI_COM, petit_commercant.getPostnom_a());
        contentValues.put(DBConstants.COLUMN_GENDER_PETI_COM, petit_commercant.getGender_a());
        contentValues.put(DBConstants.COLUMN_PHONE_PETI_COM, petit_commercant.getPhone_a());
        contentValues.put(DBConstants.COLUMN_PASSWORD_PETI_COM, petit_commercant.getMots_de_passe_a());
        contentValues.put(DBConstants.COLUMN_PASSWORD_CONFIRM_PETI_COM, petit_commercant.getMots_de_passe_conf_a());
        contentValues.put(DBConstants.COLUMN_ADDRESS_PETI_COM, petit_commercant.getAdresse_a());
        contentValues.put(DBConstants.COLUMN_DOMAINE_PETI_COM, petit_commercant.getDomaine());

        contentValues.put(DBConstants.CONTACT_PHOTO_PETI_COM, petit_commercant.getContactPhoto());

        contentValues.put(DBConstants.COLUMN_IS_CHIEF_GROUP_PETI_COM, petit_commercant.getIs_chef_group());
        contentValues.put(DBConstants.COLUMN_IS_SYNC_PETI_COM, petit_commercant.getIs_sync_a());
        contentValues.put(DBConstants.COLUMN_IS_VALIDATE_PETI_COM, petit_commercant.getIs_validate_a());
        contentValues.put(DBConstants.COLUMN_IS_UPDATE_PETI_COM, petit_commercant.getIs_update_a());
        contentValues.put(DBConstants.COLUMN_ID_GROUP_PETI_COM, petit_commercant.getId_group());

        return database.insert(DBConstants.TABLE_NAME_PETIT_COM, null, contentValues) > -1;
    }

    /**
     * * Employer
     **/
    public boolean insertEmployer(Employer employer) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.COLUMN_NAME_EMPLOYER, employer.getNom_a());
        contentValues.put(DBConstants.COLUMN_LAST_NAME_EMPLOYER, employer.getPostnom_a());
        contentValues.put(DBConstants.COLUMN_GENDER_EMPLOYER, employer.getGender_a());
        contentValues.put(DBConstants.COLUMN_PHONE_EMPLOYER, employer.getPhone_a());
        contentValues.put(DBConstants.COLUMN_PASSWORD_EMPLOYER, employer.getMots_de_passe_a());
        contentValues.put(DBConstants.COLUMN_PASSWORD_CONFIRM_EMPLOYER, employer.getMots_de_passe_conf_a());
        contentValues.put(DBConstants.COLUMN_ADDRESS_EMPLOYER, employer.getAdresse_a());
        contentValues.put(DBConstants.COLUMN_EMPLOYEUR_EMPLOYER, employer.getEmployeur());

        contentValues.put(DBConstants.CONTACT_PHOTO_EMPLOYER, employer.getContactPhoto());

        contentValues.put(DBConstants.COLUMN_IS_CHIEF_GROUP_EMPLOYER, employer.getIs_chef_group());
        contentValues.put(DBConstants.COLUMN_IS_SYNC_EMPLOYER, employer.getIs_sync_a());
        contentValues.put(DBConstants.COLUMN_IS_VALIDATE_EMPLOYER, employer.getIs_validate_a());
        contentValues.put(DBConstants.COLUMN_IS_UPDATE_EMPLOYER, employer.getIs_update_a());
        contentValues.put(DBConstants.COLUMN_ID_GROUP_EMPLOYER, employer.getId_group());

        return database.insert(DBConstants.TABLE_NAME_EMPLOYER, null, contentValues) > -1;
    }

    /**
     * * Entrepreneur
     **/
    public boolean insertEntrepreneur(Entrepreneurs employer) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.COLUMN_NAME_ENTREPRENEUR, employer.getNom_a());
        contentValues.put(DBConstants.COLUMN_LAST_NAME_ENTREPRENEUR, employer.getPostnom_a());
        contentValues.put(DBConstants.COLUMN_GENDER_ENTREPRENEUR, employer.getGender_a());
        contentValues.put(DBConstants.COLUMN_PHONE_ENTREPRENEUR, employer.getPhone_a());
        contentValues.put(DBConstants.COLUMN_PASSWORD_ENTREPRENEUR, employer.getMots_de_passe_a());
        contentValues.put(DBConstants.COLUMN_PASSWORD_CONFIRM_ENTREPRENEUR, employer.getMots_de_passe_conf_a());
        contentValues.put(DBConstants.COLUMN_ADDRESS_ENTREPRENEUR, employer.getAdresse_a());
        contentValues.put(DBConstants.COLUMN_NOM_ENTREPRISE_ENTREPRENEUR, employer.getNom_entreprise());

        contentValues.put(DBConstants.CONTACT_PHOTO_ENTREPRENEUR, employer.getContactPhoto());

        contentValues.put(DBConstants.COLUMN_IS_CHIEF_GROUP_ENTREPRENEUR, employer.getIs_chef_group());
        contentValues.put(DBConstants.COLUMN_IS_SYNC_ENTREPRENEUR, employer.getIs_sync_a());
        contentValues.put(DBConstants.COLUMN_IS_VALIDATE_ENTREPRENEUR, employer.getIs_validate_a());
        contentValues.put(DBConstants.COLUMN_IS_UPDATE_ENTREPRENEUR, employer.getIs_update_a());
        contentValues.put(DBConstants.COLUMN_ID_GROUP_ENTREPRENEUR, employer.getId_group());

        return database.insert(DBConstants.TABLE_NAME_ENTREPRENEUR, null, contentValues) > -1;
    }


    /**
     * personnes
     **/
    public ArrayList<Personnes> readPersonnes() {
        ArrayList<Personnes> list = new ArrayList<>();
        try {
            Cursor cursor_a;
            Cursor cursor_p;
            Cursor cursor_em;
            Cursor cursor_entre;
            database = dbHelper.getReadableDatabase();
            cursor_a = database.rawQuery(DBConstants.SELECT_AGRICULTEUR_QUERY, null);
            cursor_p = database.rawQuery(DBConstants.SELECT_PETIT_COM_QUERY, null);
            cursor_em = database.rawQuery(DBConstants.SELECT_EMPLOYER_QUERY, null);
            cursor_entre = database.rawQuery(DBConstants.SELECT_ENTREPRENEUR_QUERY, null);
            int a = cursor_a.getCount();
            int b = cursor_p.getCount();
            int c = cursor_em.getCount();
            int d = cursor_entre.getCount();
            list.clear();
            if (cursor_a.getCount() > 0) {
                if (cursor_a.moveToFirst()) {
                    do {
                        //select Tagriculteeur
                        int id_a = cursor_a.getInt(cursor_a.getColumnIndex(DBConstants.COLUMN_ID_AGRI));
                        String nom_a = cursor_a.getString(cursor_a.getColumnIndex(DBConstants.COLUMN_NAME_AGRI));
                        String phone_a = cursor_a.getString(cursor_a.getColumnIndex(DBConstants.COLUMN_PHONE_AGRI));
                        String postnom_a = cursor_a.getString(cursor_a.getColumnIndex(DBConstants.COLUMN_LAST_NAME_AGRI));
                        String gender_a = cursor_a.getString(cursor_a.getColumnIndex(DBConstants.COLUMN_GENDER_AGRI));
                        String mots_de_passe_a = cursor_a.getString(cursor_a.getColumnIndex(DBConstants.COLUMN_PASSWORD_AGRI));
                        String mots_de_passe_conf_a = cursor_a.getString(cursor_a.getColumnIndex(DBConstants.COLUMN_PASSWORD_CONFIRM_AGRI));
                        String adresse_a = cursor_a.getString(cursor_a.getColumnIndex(DBConstants.COLUMN_ADDRESS_AGRI));

                        byte[] conPhoto_a = cursor_a.getBlob(cursor_a.getColumnIndex(DBConstants.CONTACT_PHOTO_AGRI));

                        int is_sync_a = cursor_a.getInt(cursor_a.getColumnIndex(DBConstants.COLUMN_IS_SYNC_AGRI));
                        int is_validate_a = cursor_a.getInt(cursor_a.getColumnIndex(DBConstants.COLUMN_IS_VALIDATE_AGRI));
                        int is_update_a = cursor_a.getInt(cursor_a.getColumnIndex(DBConstants.COLUMN_IS_UPDATE_AGRI));
                        int is_chief_group_a = cursor_a.getInt(cursor_a.getColumnIndex(DBConstants.COLUMN_IS_CHIEF_GROUP_AGRI));
                        int id_group_a = cursor_a.getInt(cursor_a.getColumnIndex(DBConstants.COLUMN_ID_GROUP_AGRI));
                        int default_type_a = cursor_a.getInt(cursor_a.getColumnIndex(DBConstants.COLUMN_DEFAULT_TYPE_AGRI));

                        Agriculteurs agriculteurs = new Agriculteurs(id_a, nom_a, phone_a, postnom_a, gender_a, mots_de_passe_a,
                                mots_de_passe_conf_a, adresse_a, is_sync_a, is_validate_a, is_update_a,
                                conPhoto_a, id_group_a, default_type_a, is_chief_group_a);
                        list.add(agriculteurs);


                    } while (cursor_a.moveToNext());
                }
            }

            //petit commercant
            if (cursor_p.getCount() > 0) {
                if (cursor_p.moveToFirst()) {
                    do {


                        //select PETIT COM
                        int id_P = cursor_p.getInt(cursor_p.getColumnIndex(DBConstants.COLUMN_ID_PETI_COM));
                        String nom_P = cursor_p.getString(cursor_p.getColumnIndex(DBConstants.COLUMN_NAME_PETI_COM));
                        String phone_P = cursor_p.getString(cursor_p.getColumnIndex(DBConstants.COLUMN_PHONE_PETI_COM));
                        String postnom_P = cursor_p.getString(cursor_p.getColumnIndex(DBConstants.COLUMN_LAST_NAME_PETI_COM));
                        String gender_P = cursor_p.getString(cursor_p.getColumnIndex(DBConstants.COLUMN_GENDER_PETI_COM));
                        String mots_de_passe_P = cursor_p.getString(cursor_p.getColumnIndex(DBConstants.COLUMN_PASSWORD_PETI_COM));
                        String mots_de_passe_conf_P = cursor_p.getString(cursor_p.getColumnIndex(DBConstants.COLUMN_PASSWORD_CONFIRM_PETI_COM));
                        String adresse_P = cursor_p.getString(cursor_p.getColumnIndex(DBConstants.COLUMN_ADDRESS_PETI_COM));

                        byte[] conPhoto_P = cursor_p.getBlob(cursor_p.getColumnIndex(DBConstants.CONTACT_PHOTO_PETI_COM));

                        int is_sync_P = cursor_p.getInt(cursor_p.getColumnIndex(DBConstants.COLUMN_IS_SYNC_PETI_COM));
                        int is_validate_P = cursor_p.getInt(cursor_p.getColumnIndex(DBConstants.COLUMN_IS_VALIDATE_PETI_COM));
                        int is_update_P = cursor_p.getInt(cursor_p.getColumnIndex(DBConstants.COLUMN_IS_UPDATE_PETI_COM));
                        int is_chief_group_P = cursor_p.getInt(cursor_p.getColumnIndex(DBConstants.COLUMN_IS_CHIEF_GROUP_PETI_COM));
                        int id_group_P = cursor_p.getInt(cursor_p.getColumnIndex(DBConstants.COLUMN_ID_GROUP_PETI_COM));
                        int default_type_P = cursor_p.getInt(cursor_p.getColumnIndex(DBConstants.COLUMN_DEFAULT_TYPE_PETI_COM));

                        Petit_commercant petit_commercant = new Petit_commercant(id_P, nom_P, phone_P, postnom_P, gender_P, mots_de_passe_P,
                                mots_de_passe_conf_P, adresse_P, is_sync_P, is_validate_P, is_update_P,
                                conPhoto_P, id_group_P, default_type_P, is_chief_group_P);
                        list.add(petit_commercant);


                    } while (cursor_p.moveToNext());
                }
            }

            //employer
            if (cursor_em.getCount() > 0) {
                if (cursor_em.moveToFirst()) {
                    do {

                        //employer
                        int id_em = cursor_em.getInt(cursor_em.getColumnIndex(DBConstants.COLUMN_ID_EMPLOYER));
                        String nom_em = cursor_em.getString(cursor_em.getColumnIndex(DBConstants.COLUMN_NAME_EMPLOYER));
                        String phone_em = cursor_em.getString(cursor_em.getColumnIndex(DBConstants.COLUMN_PHONE_EMPLOYER));
                        String postnom_em = cursor_em.getString(cursor_em.getColumnIndex(DBConstants.COLUMN_LAST_NAME_EMPLOYER));
                        String gender_em = cursor_em.getString(cursor_em.getColumnIndex(DBConstants.COLUMN_GENDER_EMPLOYER));
                        String mots_de_passe_em = cursor_em.getString(cursor_em.getColumnIndex(DBConstants.COLUMN_PASSWORD_EMPLOYER));
                        String mots_de_passe_conf_em = cursor_em.getString(cursor_em.getColumnIndex(DBConstants.COLUMN_PASSWORD_CONFIRM_EMPLOYER));
                        String adresse_em = cursor_em.getString(cursor_em.getColumnIndex(DBConstants.COLUMN_ADDRESS_EMPLOYER));

                        byte[] conPhoto_em = cursor_em.getBlob(cursor_em.getColumnIndex(DBConstants.CONTACT_PHOTO_EMPLOYER));

                        int is_sync_em = cursor_em.getInt(cursor_em.getColumnIndex(DBConstants.COLUMN_IS_SYNC_EMPLOYER));
                        int is_validate_em = cursor_em.getInt(cursor_em.getColumnIndex(DBConstants.COLUMN_IS_VALIDATE_EMPLOYER));
                        int is_update_em = cursor_em.getInt(cursor_em.getColumnIndex(DBConstants.COLUMN_IS_UPDATE_EMPLOYER));
                        int is_chief_group_em = cursor_em.getInt(cursor_em.getColumnIndex(DBConstants.COLUMN_IS_CHIEF_GROUP_EMPLOYER));
                        int id_group_em = cursor_em.getInt(cursor_em.getColumnIndex(DBConstants.COLUMN_ID_GROUP_EMPLOYER));
                        int default_type_em = cursor_em.getInt(cursor_em.getColumnIndex(DBConstants.COLUMN_DEFAULT_TYPE_EMPLOYER));

                        Employer employer = new Employer(id_em, nom_em, phone_em, postnom_em, gender_em, mots_de_passe_em,
                                mots_de_passe_conf_em, adresse_em, is_sync_em, is_validate_em, is_update_em,
                                conPhoto_em, id_group_em, default_type_em, is_chief_group_em);
                        list.add(employer);


                    } while (cursor_em.moveToNext());
                }
            }

            //entreprenneur
            if (cursor_entre.getCount() > 0) {
                if (cursor_entre.moveToFirst()) {
                    do {
                        //entrepreneur
                        int id_entre = cursor_entre.getInt(cursor_entre.getColumnIndex(DBConstants.COLUMN_ID_ENTREPRENEUR));
                        String nom_entre = cursor_entre.getString(cursor_entre.getColumnIndex(DBConstants.COLUMN_NAME_ENTREPRENEUR));
                        String phone_entre = cursor_entre.getString(cursor_entre.getColumnIndex(DBConstants.COLUMN_PHONE_ENTREPRENEUR));
                        String postnom_entre = cursor_entre.getString(cursor_entre.getColumnIndex(DBConstants.COLUMN_LAST_NAME_ENTREPRENEUR));
                        String gender_entre = cursor_entre.getString(cursor_entre.getColumnIndex(DBConstants.COLUMN_GENDER_ENTREPRENEUR));
                        String mots_de_passe_entre = cursor_entre.getString(cursor_entre.getColumnIndex(DBConstants.COLUMN_PASSWORD_ENTREPRENEUR));
                        String mots_de_passe_conf_entre = cursor_entre.getString(cursor_entre.getColumnIndex(DBConstants.COLUMN_PASSWORD_CONFIRM_ENTREPRENEUR));
                        String adresse_entre = cursor_entre.getString(cursor_entre.getColumnIndex(DBConstants.COLUMN_ADDRESS_ENTREPRENEUR));

                        byte[] conPhoto_entre = cursor_entre.getBlob(cursor_entre.getColumnIndex(DBConstants.CONTACT_PHOTO_ENTREPRENEUR));

                        int is_sync_entre = cursor_entre.getInt(cursor_entre.getColumnIndex(DBConstants.COLUMN_IS_SYNC_ENTREPRENEUR));
                        int is_validate_entre = cursor_entre.getInt(cursor_entre.getColumnIndex(DBConstants.COLUMN_IS_VALIDATE_ENTREPRENEUR));
                        int is_update_entre = cursor_entre.getInt(cursor_entre.getColumnIndex(DBConstants.COLUMN_IS_UPDATE_ENTREPRENEUR));
                        int is_chief_group_entre = cursor_entre.getInt(cursor_entre.getColumnIndex(DBConstants.COLUMN_IS_CHIEF_GROUP_ENTREPRENEUR));
                        int id_group_entre = cursor_entre.getInt(cursor_entre.getColumnIndex(DBConstants.COLUMN_ID_GROUP_ENTREPRENEUR));
                        int default_type_entre = cursor_entre.getInt(cursor_entre.getColumnIndex(DBConstants.COLUMN_DEFAULT_TYPE_ENTREPRENEUR));

                        Entrepreneurs entrepreneurs = new Entrepreneurs(id_entre, nom_entre, phone_entre, postnom_entre, gender_entre, mots_de_passe_entre,
                                mots_de_passe_conf_entre, adresse_entre, is_sync_entre, is_validate_entre, is_update_entre,
                                conPhoto_entre, id_group_entre, default_type_entre, is_chief_group_entre);
                        list.add(entrepreneurs);


                    } while (cursor_entre.moveToNext());
                }
            }


            cursor_a.close();
            cursor_p.close();
            cursor_em.close();
            cursor_entre.close();
        } catch (Exception e) {
            Log.v("Exception", e.getMessage());
        }
        return list;
    }

    public Personnes readOnePersonnes(String phone) {
        Personnes p = new Personnes();
        try {
            Cursor cursor_a;
            Cursor cursor_p;
            Cursor cursor_em;
            Cursor cursor_entre;
            database = dbHelper.getReadableDatabase();
            cursor_a = database.rawQuery(DBConstants.SELECT_AGRICULTEUR_QUERY, null);
            cursor_p = database.rawQuery(DBConstants.SELECT_PETIT_COM_QUERY, null);
            cursor_em = database.rawQuery(DBConstants.SELECT_EMPLOYER_QUERY, null);
            cursor_entre = database.rawQuery(DBConstants.SELECT_ENTREPRENEUR_QUERY, null);
            int a = cursor_a.getCount();
            int b = cursor_p.getCount();
            int c = cursor_em.getCount();
            int d = cursor_entre.getCount();
            if (cursor_a.getCount() > 0) {
                if (cursor_a.moveToFirst()) {
                    do {
                        //select Tagriculteeur
                        int id_a = cursor_a.getInt(cursor_a.getColumnIndex(DBConstants.COLUMN_ID_AGRI));
                        String nom_a = cursor_a.getString(cursor_a.getColumnIndex(DBConstants.COLUMN_NAME_AGRI));
                        String phone_a = cursor_a.getString(cursor_a.getColumnIndex(DBConstants.COLUMN_PHONE_AGRI));
                        String postnom_a = cursor_a.getString(cursor_a.getColumnIndex(DBConstants.COLUMN_LAST_NAME_AGRI));
                        String gender_a = cursor_a.getString(cursor_a.getColumnIndex(DBConstants.COLUMN_GENDER_AGRI));
                        String mots_de_passe_a = cursor_a.getString(cursor_a.getColumnIndex(DBConstants.COLUMN_PASSWORD_AGRI));
                        String mots_de_passe_conf_a = cursor_a.getString(cursor_a.getColumnIndex(DBConstants.COLUMN_PASSWORD_CONFIRM_AGRI));
                        String adresse_a = cursor_a.getString(cursor_a.getColumnIndex(DBConstants.COLUMN_ADDRESS_AGRI));

                        byte[] conPhoto_a = cursor_a.getBlob(cursor_a.getColumnIndex(DBConstants.CONTACT_PHOTO_AGRI));

                        int is_sync_a = cursor_a.getInt(cursor_a.getColumnIndex(DBConstants.COLUMN_IS_SYNC_AGRI));
                        int is_validate_a = cursor_a.getInt(cursor_a.getColumnIndex(DBConstants.COLUMN_IS_VALIDATE_AGRI));
                        int is_update_a = cursor_a.getInt(cursor_a.getColumnIndex(DBConstants.COLUMN_IS_UPDATE_AGRI));
                        int is_chief_group_a = cursor_a.getInt(cursor_a.getColumnIndex(DBConstants.COLUMN_IS_CHIEF_GROUP_AGRI));
                        int id_group_a = cursor_a.getInt(cursor_a.getColumnIndex(DBConstants.COLUMN_ID_GROUP_AGRI));
                        int default_type_a = cursor_a.getInt(cursor_a.getColumnIndex(DBConstants.COLUMN_DEFAULT_TYPE_AGRI));

                        if (phone.equals(phone_a)) {
                            Personnes personnes = new Agriculteurs(id_a, nom_a, phone_a, postnom_a, gender_a, mots_de_passe_a,
                                    mots_de_passe_conf_a, adresse_a, is_sync_a, is_validate_a, is_update_a,
                                    conPhoto_a, id_group_a, default_type_a, is_chief_group_a);
                            p = personnes;
                            break;
                        }


                    } while (cursor_a.moveToNext());
                }
            }

            //petit commercant
            if (cursor_p.getCount() > 0) {
                if (cursor_p.moveToFirst()) {
                    do {


                        //select PETIT COM
                        int id_P = cursor_p.getInt(cursor_p.getColumnIndex(DBConstants.COLUMN_ID_PETI_COM));
                        String nom_P = cursor_p.getString(cursor_p.getColumnIndex(DBConstants.COLUMN_NAME_PETI_COM));
                        String phone_P = cursor_p.getString(cursor_p.getColumnIndex(DBConstants.COLUMN_PHONE_PETI_COM));
                        String postnom_P = cursor_p.getString(cursor_p.getColumnIndex(DBConstants.COLUMN_LAST_NAME_PETI_COM));
                        String gender_P = cursor_p.getString(cursor_p.getColumnIndex(DBConstants.COLUMN_GENDER_PETI_COM));
                        String mots_de_passe_P = cursor_p.getString(cursor_p.getColumnIndex(DBConstants.COLUMN_PASSWORD_PETI_COM));
                        String mots_de_passe_conf_P = cursor_p.getString(cursor_p.getColumnIndex(DBConstants.COLUMN_PASSWORD_CONFIRM_PETI_COM));
                        String adresse_P = cursor_p.getString(cursor_p.getColumnIndex(DBConstants.COLUMN_ADDRESS_PETI_COM));

                        byte[] conPhoto_P = cursor_p.getBlob(cursor_p.getColumnIndex(DBConstants.CONTACT_PHOTO_PETI_COM));

                        int is_sync_P = cursor_p.getInt(cursor_p.getColumnIndex(DBConstants.COLUMN_IS_SYNC_PETI_COM));
                        int is_validate_P = cursor_p.getInt(cursor_p.getColumnIndex(DBConstants.COLUMN_IS_VALIDATE_PETI_COM));
                        int is_update_P = cursor_p.getInt(cursor_p.getColumnIndex(DBConstants.COLUMN_IS_UPDATE_PETI_COM));
                        int is_chief_group_P = cursor_p.getInt(cursor_p.getColumnIndex(DBConstants.COLUMN_IS_CHIEF_GROUP_PETI_COM));
                        int id_group_P = cursor_p.getInt(cursor_p.getColumnIndex(DBConstants.COLUMN_ID_GROUP_PETI_COM));
                        int default_type_P = cursor_p.getInt(cursor_p.getColumnIndex(DBConstants.COLUMN_DEFAULT_TYPE_PETI_COM));

                        if (phone.equals(phone_P)) {
                            Personnes personnes = new Petit_commercant(id_P, nom_P, phone_P, postnom_P, gender_P, mots_de_passe_P,
                                    mots_de_passe_conf_P, adresse_P, is_sync_P, is_validate_P, is_update_P,
                                    conPhoto_P, id_group_P, default_type_P, is_chief_group_P);
                            p = personnes;
                            break;
                        }


                    } while (cursor_p.moveToNext());
                }
            }

            //employer
            if (cursor_em.getCount() > 0) {
                if (cursor_em.moveToFirst()) {
                    do {

                        //employer
                        int id_em = cursor_em.getInt(cursor_em.getColumnIndex(DBConstants.COLUMN_ID_EMPLOYER));
                        String nom_em = cursor_em.getString(cursor_em.getColumnIndex(DBConstants.COLUMN_NAME_EMPLOYER));
                        String phone_em = cursor_em.getString(cursor_em.getColumnIndex(DBConstants.COLUMN_PHONE_EMPLOYER));
                        String postnom_em = cursor_em.getString(cursor_em.getColumnIndex(DBConstants.COLUMN_LAST_NAME_EMPLOYER));
                        String gender_em = cursor_em.getString(cursor_em.getColumnIndex(DBConstants.COLUMN_GENDER_EMPLOYER));
                        String mots_de_passe_em = cursor_em.getString(cursor_em.getColumnIndex(DBConstants.COLUMN_PASSWORD_EMPLOYER));
                        String mots_de_passe_conf_em = cursor_em.getString(cursor_em.getColumnIndex(DBConstants.COLUMN_PASSWORD_CONFIRM_EMPLOYER));
                        String adresse_em = cursor_em.getString(cursor_em.getColumnIndex(DBConstants.COLUMN_ADDRESS_EMPLOYER));

                        byte[] conPhoto_em = cursor_em.getBlob(cursor_em.getColumnIndex(DBConstants.CONTACT_PHOTO_EMPLOYER));

                        int is_sync_em = cursor_em.getInt(cursor_em.getColumnIndex(DBConstants.COLUMN_IS_SYNC_EMPLOYER));
                        int is_validate_em = cursor_em.getInt(cursor_em.getColumnIndex(DBConstants.COLUMN_IS_VALIDATE_EMPLOYER));
                        int is_update_em = cursor_em.getInt(cursor_em.getColumnIndex(DBConstants.COLUMN_IS_UPDATE_EMPLOYER));
                        int is_chief_group_em = cursor_em.getInt(cursor_em.getColumnIndex(DBConstants.COLUMN_IS_CHIEF_GROUP_EMPLOYER));
                        int id_group_em = cursor_em.getInt(cursor_em.getColumnIndex(DBConstants.COLUMN_ID_GROUP_EMPLOYER));
                        int default_type_em = cursor_em.getInt(cursor_em.getColumnIndex(DBConstants.COLUMN_DEFAULT_TYPE_EMPLOYER));

                        if (phone.equals(phone_em)) {
                            Personnes personnes = new Employer(id_em, nom_em, phone_em, postnom_em, gender_em, mots_de_passe_em,
                                    mots_de_passe_conf_em, adresse_em, is_sync_em, is_validate_em, is_update_em,
                                    conPhoto_em, id_group_em, default_type_em, is_chief_group_em);
                            p = personnes;
                            break;
                        }


                    } while (cursor_em.moveToNext());
                }
            }

            //entreprenneur
            if (cursor_entre.getCount() > 0) {
                if (cursor_entre.moveToFirst()) {
                    do {
                        //entrepreneur
                        int id_entre = cursor_entre.getInt(cursor_entre.getColumnIndex(DBConstants.COLUMN_ID_ENTREPRENEUR));
                        String nom_entre = cursor_entre.getString(cursor_entre.getColumnIndex(DBConstants.COLUMN_NAME_ENTREPRENEUR));
                        String phone_entre = cursor_entre.getString(cursor_entre.getColumnIndex(DBConstants.COLUMN_PHONE_ENTREPRENEUR));
                        String postnom_entre = cursor_entre.getString(cursor_entre.getColumnIndex(DBConstants.COLUMN_LAST_NAME_ENTREPRENEUR));
                        String gender_entre = cursor_entre.getString(cursor_entre.getColumnIndex(DBConstants.COLUMN_GENDER_ENTREPRENEUR));
                        String mots_de_passe_entre = cursor_entre.getString(cursor_entre.getColumnIndex(DBConstants.COLUMN_PASSWORD_ENTREPRENEUR));
                        String mots_de_passe_conf_entre = cursor_entre.getString(cursor_entre.getColumnIndex(DBConstants.COLUMN_PASSWORD_CONFIRM_ENTREPRENEUR));
                        String adresse_entre = cursor_entre.getString(cursor_entre.getColumnIndex(DBConstants.COLUMN_ADDRESS_ENTREPRENEUR));

                        byte[] conPhoto_entre = cursor_entre.getBlob(cursor_entre.getColumnIndex(DBConstants.CONTACT_PHOTO_ENTREPRENEUR));

                        int is_sync_entre = cursor_entre.getInt(cursor_entre.getColumnIndex(DBConstants.COLUMN_IS_SYNC_ENTREPRENEUR));
                        int is_validate_entre = cursor_entre.getInt(cursor_entre.getColumnIndex(DBConstants.COLUMN_IS_VALIDATE_ENTREPRENEUR));
                        int is_update_entre = cursor_entre.getInt(cursor_entre.getColumnIndex(DBConstants.COLUMN_IS_UPDATE_ENTREPRENEUR));
                        int is_chief_group_entre = cursor_entre.getInt(cursor_entre.getColumnIndex(DBConstants.COLUMN_IS_CHIEF_GROUP_ENTREPRENEUR));
                        int id_group_entre = cursor_entre.getInt(cursor_entre.getColumnIndex(DBConstants.COLUMN_ID_GROUP_ENTREPRENEUR));
                        int default_type_entre = cursor_entre.getInt(cursor_entre.getColumnIndex(DBConstants.COLUMN_DEFAULT_TYPE_ENTREPRENEUR));

                        if (phone.equals(phone_entre)) {
                            Personnes personnes = new Entrepreneurs(id_entre, nom_entre, phone_entre, postnom_entre, gender_entre, mots_de_passe_entre,
                                    mots_de_passe_conf_entre, adresse_entre, is_sync_entre, is_validate_entre, is_update_entre,
                                    conPhoto_entre, id_group_entre, default_type_entre, is_chief_group_entre);
                            p = personnes;
                            break;
                        }


                    } while (cursor_entre.moveToNext());
                }
            }


            cursor_a.close();
            cursor_p.close();
            cursor_em.close();
            cursor_entre.close();
        } catch (Exception e) {
            Log.v("Exception", e.getMessage());
        }
        return p;
    }

    public boolean checkPersonne(String phone) {
        boolean return_ = false;
        try {
            Cursor cursor_a;
            Cursor cursor_p;
            Cursor cursor_em;
            Cursor cursor_entre;
            database = dbHelper.getReadableDatabase();
            cursor_a = database.rawQuery(DBConstants.SELECT_AGRICULTEUR_QUERY, null);
            cursor_p = database.rawQuery(DBConstants.SELECT_PETIT_COM_QUERY, null);
            cursor_em = database.rawQuery(DBConstants.SELECT_EMPLOYER_QUERY, null);
            cursor_entre = database.rawQuery(DBConstants.SELECT_ENTREPRENEUR_QUERY, null);
            int a = cursor_a.getCount();
            int b = cursor_p.getCount();
            int c = cursor_em.getCount();
            int d = cursor_entre.getCount();
            if (cursor_a.getCount() > 0) {
                if (cursor_a.moveToFirst()) {
                    do {
                        //select Tagriculteeur
                        String phone_a = cursor_a.getString(cursor_a.getColumnIndex(DBConstants.COLUMN_PHONE_AGRI));
                        if (phone.equals(phone_a)) {
                            return_ = true;
                            break;
                        }
                    } while (cursor_a.moveToNext());
                }
            }

            //petit commercant
            if (cursor_p.getCount() > 0) {
                if (cursor_p.moveToFirst()) {
                    do {
                        String phone_p = cursor_p.getString(cursor_p.getColumnIndex(DBConstants.COLUMN_PHONE_PETI_COM));

                        if (phone.equals(phone_p)) {
                            return_ = true;
                            break;
                        }

                    } while (cursor_p.moveToNext());
                }
            }

            //employer
            if (cursor_em.getCount() > 0) {
                if (cursor_em.moveToFirst()) {
                    do {
                        //employer
                        String phone_em = cursor_em.getString(cursor_em.getColumnIndex(DBConstants.COLUMN_PHONE_EMPLOYER));
                        if (phone.equals(phone_em)) {
                            return_ = true;
                            break;
                        }

                    } while (cursor_em.moveToNext());
                }
            }

            //entreprenneur
            if (cursor_entre.getCount() > 0) {
                if (cursor_entre.moveToFirst()) {
                    do {
                        //entrepreneur

                        String phone_entre = cursor_entre.getString(cursor_entre.getColumnIndex(DBConstants.COLUMN_PHONE_ENTREPRENEUR));
                        if (phone.equals(phone_entre)) {
                            return_ = true;
                            break;
                        }

                    } while (cursor_entre.moveToNext());
                }
            }
            cursor_a.close();
            cursor_p.close();
            cursor_em.close();
            cursor_entre.close();
        } catch (Exception e) {
            Log.v("Exception", e.getMessage());
            return return_;
        }
        return return_;
    }

}
