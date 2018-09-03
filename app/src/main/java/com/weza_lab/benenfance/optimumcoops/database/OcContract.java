package com.weza_lab.benenfance.optimumcoops.database;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class OcContract {

    public static final String CONTENT_AUTHORITY = "com.weza_lab.benenfance.optimumcoops";
    public static final Uri BASE_CONTENT = Uri.parse("content://" + CONTENT_AUTHORITY);


    public static final String PATH_AGRICULTEUR = "agriculteurs";
    public static final String PATH_USER = "users";

    public static final class Agriculteurs implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT.buildUpon().appendPath(PATH_AGRICULTEUR).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_AGRICULTEUR;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_AGRICULTEUR;

        public static final String TABLE_NAME = "agriculteurs";

        public static final String COLUMN_NAME = "agri_name";
        public static final String COLUMN_LAST_NAME = "agri_last_name";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_PASSWORD_CONFIRM = "password_confirm";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_PLANTATION = "plantation";
        //public static final String COLUMN_COURS_EAU = "cours_eau_proche";


        public static final String COLUMN_IS_SYNC = "is_sync";
        public static final String COLUMN_IS_UPDATE = "is_update";
        public static final String COLUMN_IS_VALIDATE = "agri_valide";
        public static final String COLUMN_IS_CHIEF_GROUP = "chief_group";

        public static Uri buildFarmerUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class Users implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT.buildUpon().appendPath(PATH_USER).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        public static final String TABLE_NAME = "users";

        public static final String COLUMN_USER_NAME = "user_name";


        public static Uri buildUserUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

}
