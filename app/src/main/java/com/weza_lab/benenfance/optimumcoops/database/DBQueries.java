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
                        int dafault_type = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_DEFAULT_TYPE));

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
                        int dafault_type = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_DEFAULT_TYPE));

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
            Cursor cursor;
            database = dbHelper.getReadableDatabase();
            cursor = database.rawQuery(DBConstants.SELECT_PERSONNE_QUERY, null);
            list.clear();
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        //select TOUS les types des personnes
                        int id_a = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_ID_AGRI));
                        String nom_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_NAME_AGRI));
                        String phone_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_PHONE_AGRI));
                        String postnom_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_LAST_NAME_AGRI));
                        String gender_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_GENDER_AGRI));
                        String mots_de_passe_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_PASSWORD_AGRI));
                        String mots_de_passe_conf_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_PASSWORD_CONFIRM_AGRI));
                        String adresse_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_ADDRESS_AGRI));

                        byte[] conPhoto = cursor.getBlob(cursor.getColumnIndex(DBConstants.CONTACT_PHOTO_AGRI));

                        int is_sync_a = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_IS_SYNC_AGRI));
                        int is_validate_a = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_IS_VALIDATE_AGRI));
                        int is_update_a = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_IS_UPDATE_AGRI));
                        int is_chief_group = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_IS_CHIEF_GROUP_AGRI));
                        int id_group = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_ID_GROUP_AGRI));
                        int default_type = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_DEFAULT_TYPE));

                        Personnes personnes = new Personnes(id_a, nom_a, phone_a, postnom_a, gender_a, mots_de_passe_a,
                                mots_de_passe_conf_a, adresse_a, is_sync_a, is_validate_a, is_update_a,
                                conPhoto, id_group, default_type, is_chief_group);
                        list.add(personnes);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
        } catch (Exception e) {
            Log.v("Exception", e.getMessage());
        }
        return list;
    }

    public Personnes readOnePersonnes(String phone) {
        Personnes p = new Personnes();
        try {
            Cursor cursor;
            database = dbHelper.getReadableDatabase();
            cursor = database.rawQuery(DBConstants.SELECT_PERSONNE_QUERY, null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        //select TOUS les types des personnes
                        int id_a = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_ID_AGRI));
                        String nom_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_NAME_AGRI));
                        String phone_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_PHONE_AGRI));
                        String postnom_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_LAST_NAME_AGRI));
                        String gender_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_GENDER_AGRI));
                        String mots_de_passe_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_PASSWORD_AGRI));
                        String mots_de_passe_conf_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_PASSWORD_CONFIRM_AGRI));
                        String adresse_a = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_ADDRESS_AGRI));

                        byte[] conPhoto = cursor.getBlob(cursor.getColumnIndex(DBConstants.CONTACT_PHOTO_AGRI));

                        int is_sync_a = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_IS_SYNC_AGRI));
                        int is_validate_a = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_IS_VALIDATE_AGRI));
                        int is_update_a = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_IS_UPDATE_AGRI));
                        int is_chief_group = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_IS_CHIEF_GROUP_AGRI));
                        int id_group = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_ID_GROUP_AGRI));
                        int default_type = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_DEFAULT_TYPE));

                        if (phone.equals(phone_a)) {
                            Personnes personnes = new Personnes(id_a, nom_a, phone_a, postnom_a, gender_a, mots_de_passe_a,
                                    mots_de_passe_conf_a, adresse_a, is_sync_a, is_validate_a, is_update_a,
                                    conPhoto, id_group, default_type, is_chief_group);
                            p = personnes;
                            break;
                        }


                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
        } catch (Exception e) {
            Log.v("Exception", e.getMessage());
        }
        return p;
    }

    public boolean checkPersonne(String phone) {
        boolean return_ = false;
        try {
            Cursor cursor;
            database = dbHelper.getReadableDatabase();
            cursor = database.rawQuery(DBConstants.SELECT_PERSONNE_QUERY, null);
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

}
