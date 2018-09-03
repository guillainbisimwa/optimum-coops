package com.weza_lab.benenfance.optimumcoops.database;

class DBConstants {

    //Premiere table user (never used)
    static final String USER_TABLE = "customers";
    static final String CONTACT_ID = "contact_id";
    static final String CONTACT_PERSON_NAME = "contact_person_name";
    static final String CONTACT_NO = "contact_no";
    static final String CONTACT_PHOTO = "contact_photo";

    static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + " ("
            + CONTACT_ID + " INTEGER PRIMARY KEY,"
            + CONTACT_PERSON_NAME + " TEXT,"
            + CONTACT_NO + " TEXT,"
            + CONTACT_PHOTO + " BLOB DEFAULT NULL)";

    static final String COLUMN_DEFAULT_TYPE = "type";


    //Deuxime table agriculteurs
    static final String TABLE_NAME_AGRICULTEURS = "agriculteurs";

    static final String COLUMN_ID_AGRI = "id";
    static final String COLUMN_NAME_AGRI = "agri_name";
    static final String COLUMN_LAST_NAME_AGRI = "agri_last_name";
    static final String COLUMN_PHONE_AGRI = "phone";
    static final String COLUMN_GENDER_AGRI = "gender";
    static final String COLUMN_PASSWORD_AGRI = "password";
    static final String COLUMN_PASSWORD_CONFIRM_AGRI = "password_confirm";
    static final String COLUMN_ADDRESS_AGRI = "address";
    static final String COLUMN_PLANTATION_AGRI = "plantation";

    static final String CONTACT_PHOTO_AGRI = "contact_photo";

    static final String COLUMN_IS_SYNC_AGRI = "is_sync";
    static final String COLUMN_IS_UPDATE_AGRI = "is_update";
    static final String COLUMN_IS_VALIDATE_AGRI = "agri_valide";
    static final String COLUMN_IS_CHIEF_GROUP_AGRI = "chief_group";
    static final String COLUMN_ID_GROUP_AGRI = "id_group";

    static final String CREATE_TABLE_AGRICULTEUR = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_AGRICULTEURS + " ( " +
            COLUMN_ID_AGRI + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME_AGRI + " TEXT NOT NULL, " +
            COLUMN_LAST_NAME_AGRI + " TEXT NOT NULL, " +
            COLUMN_GENDER_AGRI + " TEXT NOT NULL, " +
            COLUMN_PHONE_AGRI + " TEXT NOT NULL, " +
            COLUMN_PASSWORD_AGRI + " TEXT NOT NULL, " +
            COLUMN_PASSWORD_CONFIRM_AGRI + " TEXT NOT NULL, " +
            COLUMN_ADDRESS_AGRI + " TEXT NOT NULL," +
            //COLUMN_COURS_EAU_AGRI + " TEXT NOT NULL," +
            CONTACT_PHOTO_AGRI + " BLOB DEFAULT NULL, " +
            COLUMN_DEFAULT_TYPE + " INTEGER DEFAULT 100, " +

            COLUMN_ID_GROUP_AGRI + " INTEGER DEFAULT 0, " +
            COLUMN_PLANTATION_AGRI + " INTEGER, " +
            COLUMN_IS_VALIDATE_AGRI + " INTEGER, " +
            COLUMN_IS_CHIEF_GROUP_AGRI + " INTEGER, " +
            COLUMN_IS_SYNC_AGRI + " INTEGER, " +
            COLUMN_IS_UPDATE_AGRI + " INTEGER " +
            " ); ";


    //Troisieme table petit commercant
    static final String TABLE_NAME_PETIT_COM = "petit_commercant";

    static final String COLUMN_ID_PETI_COM = "id";
    static final String COLUMN_NAME_PETI_COM = "agri_name";
    static final String COLUMN_LAST_NAME_PETI_COM = "agri_last_name";
    static final String COLUMN_PHONE_PETI_COM = "phone";
    static final String COLUMN_GENDER_PETI_COM = "gender";
    static final String COLUMN_PASSWORD_PETI_COM = "password";
    static final String COLUMN_PASSWORD_CONFIRM_PETI_COM = "password_confirm";
    static final String COLUMN_ADDRESS_PETI_COM = "address";
    static final String COLUMN_DOMAINE_PETI_COM = "domaine";

    static final String CONTACT_PHOTO_PETI_COM = "contact_photo";

    static final String COLUMN_IS_SYNC_PETI_COM = "is_sync";
    static final String COLUMN_IS_UPDATE_PETI_COM = "is_update";
    static final String COLUMN_IS_VALIDATE_PETI_COM = "agri_valide";
    static final String COLUMN_IS_CHIEF_GROUP_PETI_COM = "chief_group";
    static final String COLUMN_ID_GROUP_PETI_COM = "id_group";

    static final String CREATE_TABLE_PETIT_COM = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_PETIT_COM + " ( " +
            COLUMN_ID_PETI_COM + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME_PETI_COM + " TEXT NOT NULL, " +
            COLUMN_LAST_NAME_PETI_COM + " TEXT NOT NULL, " +
            COLUMN_GENDER_PETI_COM + " TEXT NOT NULL, " +
            COLUMN_PHONE_PETI_COM + " TEXT NOT NULL, " +
            COLUMN_PASSWORD_PETI_COM + " TEXT NOT NULL, " +
            COLUMN_PASSWORD_CONFIRM_PETI_COM + " TEXT NOT NULL, " +
            COLUMN_ADDRESS_PETI_COM + " TEXT NOT NULL," +
            COLUMN_DOMAINE_PETI_COM + " TEXT NOT NULL," +
            CONTACT_PHOTO_PETI_COM + " BLOB DEFAULT NULL, " +
            COLUMN_DEFAULT_TYPE + " INTEGER DEFAULT 101, " +

            COLUMN_ID_GROUP_PETI_COM + " INTEGER DEFAULT 0, " +
            COLUMN_IS_VALIDATE_PETI_COM + " INTEGER, " +
            COLUMN_IS_CHIEF_GROUP_PETI_COM + " INTEGER, " +
            COLUMN_IS_SYNC_PETI_COM + " INTEGER, " +
            COLUMN_IS_UPDATE_PETI_COM + " INTEGER " +
            " ); ";

    //Quatrieme table employer
    static final String TABLE_NAME_EMPLOYER = "employer";

    static final String COLUMN_ID_EMPLOYER = "id";
    static final String COLUMN_NAME_EMPLOYER = "agri_name";
    static final String COLUMN_LAST_NAME_EMPLOYER = "agri_last_name";
    static final String COLUMN_PHONE_EMPLOYER = "phone";
    static final String COLUMN_GENDER_EMPLOYER = "gender";
    static final String COLUMN_PASSWORD_EMPLOYER = "password";
    static final String COLUMN_PASSWORD_CONFIRM_EMPLOYER = "password_confirm";
    static final String COLUMN_ADDRESS_EMPLOYER = "address";
    static final String COLUMN_EMPLOYEUR_EMPLOYER = "employeur";

    static final String CONTACT_PHOTO_EMPLOYER = "contact_photo";

    static final String COLUMN_IS_SYNC_EMPLOYER = "is_sync";
    static final String COLUMN_IS_UPDATE_EMPLOYER = "is_update";
    static final String COLUMN_IS_VALIDATE_EMPLOYER = "agri_valide";
    static final String COLUMN_IS_CHIEF_GROUP_EMPLOYER = "chief_group";
    static final String COLUMN_ID_GROUP_EMPLOYER = "id_group";

    static final String CREATE_TABLE_EMPLOYER = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_EMPLOYER + " ( " +
            COLUMN_ID_EMPLOYER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME_EMPLOYER + " TEXT NOT NULL, " +
            COLUMN_LAST_NAME_EMPLOYER + " TEXT NOT NULL, " +
            COLUMN_GENDER_EMPLOYER + " TEXT NOT NULL, " +
            COLUMN_PHONE_EMPLOYER + " TEXT NOT NULL, " +
            COLUMN_PASSWORD_EMPLOYER + " TEXT NOT NULL, " +
            COLUMN_PASSWORD_CONFIRM_EMPLOYER + " TEXT NOT NULL, " +
            COLUMN_ADDRESS_EMPLOYER + " TEXT NOT NULL," +
            COLUMN_EMPLOYEUR_EMPLOYER + " TEXT NOT NULL," +
            CONTACT_PHOTO_EMPLOYER + " BLOB DEFAULT NULL, " +
            COLUMN_DEFAULT_TYPE + " INTEGER DEFAULT 102, " +

            COLUMN_ID_GROUP_EMPLOYER + " INTEGER DEFAULT 0, " +
            COLUMN_IS_VALIDATE_EMPLOYER + " INTEGER, " +
            COLUMN_IS_CHIEF_GROUP_EMPLOYER + " INTEGER, " +
            COLUMN_IS_SYNC_EMPLOYER + " INTEGER, " +
            COLUMN_IS_UPDATE_EMPLOYER + " INTEGER " +
            " ); ";

    //Cinquieme table entrepreneur
    static final String TABLE_NAME_ENTREPRENEUR = "entrepreneur";

    static final String COLUMN_ID_ENTREPRENEUR = "id";
    static final String COLUMN_NAME_ENTREPRENEUR = "agri_name";
    static final String COLUMN_LAST_NAME_ENTREPRENEUR = "agri_last_name";
    static final String COLUMN_PHONE_ENTREPRENEUR = "phone";
    static final String COLUMN_GENDER_ENTREPRENEUR = "gender";
    static final String COLUMN_PASSWORD_ENTREPRENEUR = "password";
    static final String COLUMN_PASSWORD_CONFIRM_ENTREPRENEUR = "password_confirm";
    static final String COLUMN_ADDRESS_ENTREPRENEUR = "address";
    static final String COLUMN_NOM_ENTREPRISE_ENTREPRENEUR = "entreprise";

    static final String CONTACT_PHOTO_ENTREPRENEUR = "contact_photo";

    static final String COLUMN_IS_SYNC_ENTREPRENEUR = "is_sync";
    static final String COLUMN_IS_UPDATE_ENTREPRENEUR = "is_update";
    static final String COLUMN_IS_VALIDATE_ENTREPRENEUR = "agri_valide";
    static final String COLUMN_IS_CHIEF_GROUP_ENTREPRENEUR = "chief_group";
    static final String COLUMN_ID_GROUP_ENTREPRENEUR = "id_group";

    static final String CREATE_TABLE_ENTREPRENEUR = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_PETIT_COM + " ( " +
            COLUMN_ID_ENTREPRENEUR + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME_ENTREPRENEUR + " TEXT NOT NULL, " +
            COLUMN_LAST_NAME_ENTREPRENEUR + " TEXT NOT NULL, " +
            COLUMN_GENDER_ENTREPRENEUR + " TEXT NOT NULL, " +
            COLUMN_PHONE_ENTREPRENEUR + " TEXT NOT NULL, " +
            COLUMN_PASSWORD_ENTREPRENEUR + " TEXT NOT NULL, " +
            COLUMN_PASSWORD_CONFIRM_ENTREPRENEUR + " TEXT NOT NULL, " +
            COLUMN_ADDRESS_ENTREPRENEUR + " TEXT NOT NULL," +
            COLUMN_NOM_ENTREPRISE_ENTREPRENEUR + " TEXT NOT NULL," +
            CONTACT_PHOTO_ENTREPRENEUR + " BLOB DEFAULT NULL, " +
            COLUMN_DEFAULT_TYPE + " INTEGER DEFAULT 103, " +

            COLUMN_ID_GROUP_ENTREPRENEUR + " INTEGER DEFAULT 0, " +
            COLUMN_IS_VALIDATE_ENTREPRENEUR + " INTEGER, " +
            COLUMN_IS_CHIEF_GROUP_ENTREPRENEUR + " INTEGER, " +
            COLUMN_IS_SYNC_ENTREPRENEUR + " INTEGER, " +
            COLUMN_IS_UPDATE_ENTREPRENEUR + " INTEGER " +
            " ); ";

    /*
    LES REQUETES
    */
    static final String SELECT_QUERY = "SELECT * FROM " + USER_TABLE;
    static final String SELECT_AGRICULTEUR_QUERY = "SELECT * FROM " + TABLE_NAME_AGRICULTEURS;
    static final String SELECT_PETIT_COM_QUERY = "SELECT * FROM " + TABLE_NAME_PETIT_COM;
    static final String SELECT_EMPLOYER_QUERY = "SELECT * FROM " + TABLE_NAME_EMPLOYER;
    static final String SELECT_ENTREPRENEUR_QUERY = "SELECT * FROM " + TABLE_NAME_ENTREPRENEUR;

    static final String SELECT_PERSONNE_QUERY = "SELECT * FROM " + TABLE_NAME_AGRICULTEURS + " , " + TABLE_NAME_ENTREPRENEUR
            + " , " + TABLE_NAME_PETIT_COM + " , " + TABLE_NAME_EMPLOYER;


}
