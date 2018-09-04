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

    static final String COLUMN_DEFAULT_TYPE_AGRI = "type_agri";
    static final String COLUMN_DEFAULT_TYPE_PETI_COM = "type_petit_com";
    static final String COLUMN_DEFAULT_TYPE_EMPLOYER = "type_emp";
    static final String COLUMN_DEFAULT_TYPE_ENTREPRENEUR = "type_entre";


    //Deuxime table agriculteurs
    static final String TABLE_NAME_AGRICULTEURS = "agriculteurs";

    static final String COLUMN_ID_AGRI = "id_agri";
    static final String COLUMN_NAME_AGRI = "name";
    static final String COLUMN_LAST_NAME_AGRI = "last_name_agri";
    static final String COLUMN_PHONE_AGRI = "phone_agri";
    static final String COLUMN_GENDER_AGRI = "gender_agri";
    static final String COLUMN_PASSWORD_AGRI = "password";
    static final String COLUMN_PASSWORD_CONFIRM_AGRI = "password_confirm_agri";
    static final String COLUMN_ADDRESS_AGRI = "address_agri";
    static final String COLUMN_PLANTATION_AGRI = "plantation_agri";

    static final String CONTACT_PHOTO_AGRI = "contact_photo_agri";

    static final String COLUMN_IS_SYNC_AGRI = "is_sync_agri";
    static final String COLUMN_IS_UPDATE_AGRI = "is_update_agri";
    static final String COLUMN_IS_VALIDATE_AGRI = "agri_valide_agri";
    static final String COLUMN_IS_CHIEF_GROUP_AGRI = "chief_group_agri";
    static final String COLUMN_ID_GROUP_AGRI = "id_group_agri";

    static final String CREATE_TABLE_AGRICULTEUR = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_AGRICULTEURS + " ( " +
            COLUMN_ID_AGRI + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME_AGRI + " TEXT, " +
            COLUMN_LAST_NAME_AGRI + " TEXT, " +
            COLUMN_GENDER_AGRI + " TEXT, " +
            COLUMN_PHONE_AGRI + " TEXT, " +
            COLUMN_PASSWORD_AGRI + " TEXT, " +
            COLUMN_PASSWORD_CONFIRM_AGRI + " TEXT, " +
            COLUMN_ADDRESS_AGRI + " TEXT," +
            //COLUMN_COURS_EAU_AGRI + " TEXT," +
            CONTACT_PHOTO_AGRI + " BLOB DEFAULT NULL, " +
            COLUMN_DEFAULT_TYPE_AGRI + " INTEGER DEFAULT 100, " +

            COLUMN_ID_GROUP_AGRI + " INTEGER DEFAULT 0, " +
            COLUMN_PLANTATION_AGRI + " INTEGER, " +
            COLUMN_IS_VALIDATE_AGRI + " INTEGER, " +
            COLUMN_IS_CHIEF_GROUP_AGRI + " INTEGER, " +
            COLUMN_IS_SYNC_AGRI + " INTEGER, " +
            COLUMN_IS_UPDATE_AGRI + " INTEGER " +
            " ); ";


    //Troisieme table petit commercant
    static final String TABLE_NAME_PETIT_COM = "petit_commercant";

    static final String COLUMN_ID_PETI_COM = "id_petit_com";
    static final String COLUMN_NAME_PETI_COM = "name_petit_com";
    static final String COLUMN_LAST_NAME_PETI_COM = "last_name_petit_com";
    static final String COLUMN_PHONE_PETI_COM = "phone_petit_com";
    static final String COLUMN_GENDER_PETI_COM = "gender_petit_com";
    static final String COLUMN_PASSWORD_PETI_COM = "password_petit_com";
    static final String COLUMN_PASSWORD_CONFIRM_PETI_COM = "password_confirm_petit_com";
    static final String COLUMN_ADDRESS_PETI_COM = "address_petit_com";
    static final String COLUMN_DOMAINE_PETI_COM = "domaine_petit_com";

    static final String CONTACT_PHOTO_PETI_COM = "contact_photo_petit_com";

    static final String COLUMN_IS_SYNC_PETI_COM = "is_sync_petit_com";
    static final String COLUMN_IS_UPDATE_PETI_COM = "is_update_petit_com";
    static final String COLUMN_IS_VALIDATE_PETI_COM = "agri_valide_petit_com";
    static final String COLUMN_IS_CHIEF_GROUP_PETI_COM = "chief_group_petit_com";
    static final String COLUMN_ID_GROUP_PETI_COM = "id_group_petit_com";

    static final String CREATE_TABLE_PETIT_COM = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_PETIT_COM + " ( " +
            COLUMN_ID_PETI_COM + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME_PETI_COM + " TEXT, " +
            COLUMN_LAST_NAME_PETI_COM + " TEXT, " +
            COLUMN_GENDER_PETI_COM + " TEXT, " +
            COLUMN_PHONE_PETI_COM + " TEXT, " +
            COLUMN_PASSWORD_PETI_COM + " TEXT, " +
            COLUMN_PASSWORD_CONFIRM_PETI_COM + " TEXT, " +
            COLUMN_ADDRESS_PETI_COM + " TEXT," +
            COLUMN_DOMAINE_PETI_COM + " TEXT," +
            CONTACT_PHOTO_PETI_COM + " BLOB DEFAULT NULL, " +
            COLUMN_DEFAULT_TYPE_PETI_COM + " INTEGER DEFAULT 101, " +

            COLUMN_ID_GROUP_PETI_COM + " INTEGER DEFAULT 0, " +
            COLUMN_IS_VALIDATE_PETI_COM + " INTEGER, " +
            COLUMN_IS_CHIEF_GROUP_PETI_COM + " INTEGER, " +
            COLUMN_IS_SYNC_PETI_COM + " INTEGER, " +
            COLUMN_IS_UPDATE_PETI_COM + " INTEGER " +
            " ); ";

    //Quatrieme table employer
    static final String TABLE_NAME_EMPLOYER = "employer";

    static final String COLUMN_ID_EMPLOYER = "id_emp";
    static final String COLUMN_NAME_EMPLOYER = "name_emp";
    static final String COLUMN_LAST_NAME_EMPLOYER = "last_name_emp";
    static final String COLUMN_PHONE_EMPLOYER = "phone_emp";
    static final String COLUMN_GENDER_EMPLOYER = "gender_emp";
    static final String COLUMN_PASSWORD_EMPLOYER = "password_emp";
    static final String COLUMN_PASSWORD_CONFIRM_EMPLOYER = "password_confirm_emp";
    static final String COLUMN_ADDRESS_EMPLOYER = "address_emp";
    static final String COLUMN_EMPLOYEUR_EMPLOYER = "employeur_emp";

    static final String CONTACT_PHOTO_EMPLOYER = "contact_photo_emp";

    static final String COLUMN_IS_SYNC_EMPLOYER = "is_sync_emp";
    static final String COLUMN_IS_UPDATE_EMPLOYER = "is_update_emp";
    static final String COLUMN_IS_VALIDATE_EMPLOYER = "agri_valide_emp";
    static final String COLUMN_IS_CHIEF_GROUP_EMPLOYER = "chief_group_emp";
    static final String COLUMN_ID_GROUP_EMPLOYER = "id_group_emp";

    static final String CREATE_TABLE_EMPLOYER = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_EMPLOYER + " ( " +
            COLUMN_ID_EMPLOYER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME_EMPLOYER + " TEXT, " +
            COLUMN_LAST_NAME_EMPLOYER + " TEXT, " +
            COLUMN_GENDER_EMPLOYER + " TEXT, " +
            COLUMN_PHONE_EMPLOYER + " TEXT, " +
            COLUMN_PASSWORD_EMPLOYER + " TEXT, " +
            COLUMN_PASSWORD_CONFIRM_EMPLOYER + " TEXT, " +
            COLUMN_ADDRESS_EMPLOYER + " TEXT," +
            COLUMN_EMPLOYEUR_EMPLOYER + " TEXT," +
            CONTACT_PHOTO_EMPLOYER + " BLOB DEFAULT NULL, " +
            COLUMN_DEFAULT_TYPE_EMPLOYER + " INTEGER DEFAULT 102, " +

            COLUMN_ID_GROUP_EMPLOYER + " INTEGER DEFAULT 0, " +
            COLUMN_IS_VALIDATE_EMPLOYER + " INTEGER, " +
            COLUMN_IS_CHIEF_GROUP_EMPLOYER + " INTEGER, " +
            COLUMN_IS_SYNC_EMPLOYER + " INTEGER, " +
            COLUMN_IS_UPDATE_EMPLOYER + " INTEGER " +
            " ); ";

    //Cinquieme table entrepreneur
    static final String TABLE_NAME_ENTREPRENEUR = "entrepreneur";

    static final String COLUMN_ID_ENTREPRENEUR = "id_entre";
    static final String COLUMN_NAME_ENTREPRENEUR = "name_entre";
    static final String COLUMN_LAST_NAME_ENTREPRENEUR = "last_name_entre";
    static final String COLUMN_PHONE_ENTREPRENEUR = "phone_entre";
    static final String COLUMN_GENDER_ENTREPRENEUR = "gender_entre";
    static final String COLUMN_PASSWORD_ENTREPRENEUR = "password_entre";
    static final String COLUMN_PASSWORD_CONFIRM_ENTREPRENEUR = "password_confirm_entre";
    static final String COLUMN_ADDRESS_ENTREPRENEUR = "address_entre";
    static final String COLUMN_NOM_ENTREPRISE_ENTREPRENEUR = "entreprise_entre";

    static final String CONTACT_PHOTO_ENTREPRENEUR = "contact_photo_entre";

    static final String COLUMN_IS_SYNC_ENTREPRENEUR = "is_sync_entre";
    static final String COLUMN_IS_UPDATE_ENTREPRENEUR = "is_update_entre";
    static final String COLUMN_IS_VALIDATE_ENTREPRENEUR = "agri_valide_entre";
    static final String COLUMN_IS_CHIEF_GROUP_ENTREPRENEUR = "chief_group_entre";
    static final String COLUMN_ID_GROUP_ENTREPRENEUR = "id_group_entre";

    static final String CREATE_TABLE_ENTREPRENEUR = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_ENTREPRENEUR + " ( " +
            COLUMN_ID_ENTREPRENEUR + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME_ENTREPRENEUR + " TEXT, " +
            COLUMN_LAST_NAME_ENTREPRENEUR + " TEXT, " +
            COLUMN_GENDER_ENTREPRENEUR + " TEXT, " +
            COLUMN_PHONE_ENTREPRENEUR + " TEXT, " +
            COLUMN_PASSWORD_ENTREPRENEUR + " TEXT, " +
            COLUMN_PASSWORD_CONFIRM_ENTREPRENEUR + " TEXT, " +
            COLUMN_ADDRESS_ENTREPRENEUR + " TEXT," +
            COLUMN_NOM_ENTREPRISE_ENTREPRENEUR + " TEXT," +
            CONTACT_PHOTO_ENTREPRENEUR + " BLOB DEFAULT NULL, " +
            COLUMN_DEFAULT_TYPE_ENTREPRENEUR + " INTEGER DEFAULT 103, " +

            COLUMN_ID_GROUP_ENTREPRENEUR + " INTEGER DEFAULT 0, " +
            COLUMN_IS_VALIDATE_ENTREPRENEUR + " INTEGER, " +
            COLUMN_IS_CHIEF_GROUP_ENTREPRENEUR + " INTEGER, " +
            COLUMN_IS_SYNC_ENTREPRENEUR + " INTEGER, " +
            COLUMN_IS_UPDATE_ENTREPRENEUR + " INTEGER " +
            " ); ";

    /**
     * LES REQUETES
     **/
    static final String SELECT_QUERY = "SELECT * FROM " + USER_TABLE;
    static final String SELECT_AGRICULTEUR_QUERY = "SELECT * FROM " + TABLE_NAME_AGRICULTEURS;
    static final String SELECT_PETIT_COM_QUERY = "SELECT * FROM " + TABLE_NAME_PETIT_COM;
    static final String SELECT_EMPLOYER_QUERY = "SELECT * FROM " + TABLE_NAME_EMPLOYER;
    static final String SELECT_ENTREPRENEUR_QUERY = "SELECT * FROM " + TABLE_NAME_ENTREPRENEUR;

    //static final String SELECT_PERSONNE_QUERY = "SELECT * FROM " +TABLE_NAME_AGRICULTEURS+ " , " +TABLE_NAME_PETIT_COM;

    static final String SELECT_PERSONNE_QUERY = "SELECT * FROM " + TABLE_NAME_AGRICULTEURS + " , " + TABLE_NAME_ENTREPRENEUR
            + " , " + TABLE_NAME_PETIT_COM + " , " + TABLE_NAME_EMPLOYER + "WHERE 1";



}
