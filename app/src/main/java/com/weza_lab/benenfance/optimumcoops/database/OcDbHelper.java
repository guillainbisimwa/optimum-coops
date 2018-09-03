package com.weza_lab.benenfance.optimumcoops.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OcDbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "optimum_coops.db";

    public OcDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_TABLE_FARMER = "CREATE TABLE " + OcContract.Agriculteurs.TABLE_NAME + " ( " +
                OcContract.Agriculteurs._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                OcContract.Agriculteurs.COLUMN_NAME + " TEXT NOT NULL, " +
                OcContract.Agriculteurs.COLUMN_LAST_NAME + " TEXT NOT NULL, " +
                OcContract.Agriculteurs.COLUMN_GENDER + " TEXT NOT NULL, " +
                OcContract.Agriculteurs.COLUMN_PHONE + " TEXT NOT NULL, " +
                OcContract.Agriculteurs.COLUMN_PASSWORD + " TEXT NOT NULL, " +
                OcContract.Agriculteurs.COLUMN_PASSWORD_CONFIRM + " TEXT NOT NULL, " +
                OcContract.Agriculteurs.COLUMN_ADDRESS + " TEXT NOT NULL," +
                //OcContract.Agriculteurs.COLUMN_COURS_EAU + " TEXT NOT NULL," +

                OcContract.Agriculteurs.COLUMN_PLANTATION + " INTEGER, " +
                OcContract.Agriculteurs.COLUMN_IS_VALIDATE + " INTEGER, " +
                OcContract.Agriculteurs.COLUMN_IS_CHIEF_GROUP + " INTEGER, " +
                OcContract.Agriculteurs.COLUMN_IS_SYNC + " INTEGER, " +
                OcContract.Agriculteurs.COLUMN_IS_UPDATE + " INTEGER " +
                " ); ";

        final String CREATE_TABLE_USER = "CREATE TABLE " + OcContract.Users.TABLE_NAME + " ( " +
                OcContract.Users._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                OcContract.Users.COLUMN_USER_NAME + " TEXT" +
                ");";

        sqLiteDatabase.execSQL(CREATE_TABLE_FARMER);
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
