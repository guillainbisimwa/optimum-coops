package com.weza_lab.benenfance.optimumcoops.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "optimum_Coops";
    private static int DB_VERSION = 1;
    private Context context;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBConstants.CREATE_USER_TABLE);
        db.execSQL(DBConstants.CREATE_TABLE_AGRICULTEUR);
        db.execSQL(DBConstants.CREATE_TABLE_PETIT_COM);
        db.execSQL(DBConstants.CREATE_TABLE_EMPLOYER);
        db.execSQL(DBConstants.CREATE_TABLE_ENTREPRENEUR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBConstants.USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DBConstants.TABLE_NAME_AGRICULTEURS);
        db.execSQL("DROP TABLE IF EXISTS " + DBConstants.TABLE_NAME_PETIT_COM);
        db.execSQL("DROP TABLE IF EXISTS " + DBConstants.TABLE_NAME_EMPLOYER);
        db.execSQL("DROP TABLE IF EXISTS " + DBConstants.TABLE_NAME_ENTREPRENEUR);
    }

}
