package com.weza_lab.benenfance.optimumcoops.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class OcProvider extends ContentProvider {

    static final int AGRICULTEUR = 103;
    static final int USER = 250;

    public UriMatcher mUriMatcher = buildUriMatcher();
    private OcDbHelper mBfwDbHelper;

    public OcProvider() {

    }

    private UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        final String authority = OcContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, OcContract.PATH_AGRICULTEUR, AGRICULTEUR);
        matcher.addURI(authority, OcContract.PATH_USER, USER);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mBfwDbHelper = new OcDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = mUriMatcher.match(uri);
        switch (match) {
            case AGRICULTEUR:
                return OcContract.Agriculteurs.CONTENT_TYPE;
            case USER:
                return OcContract.Users.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase db = mBfwDbHelper.getWritableDatabase();
        Uri returnUri;
        long id;

        switch (mUriMatcher.match(uri)) {

            case AGRICULTEUR:
                id = db.insert(OcContract.Agriculteurs.TABLE_NAME, null, contentValues);
                if (id > 0) {
                    returnUri = OcContract.Agriculteurs.buildFarmerUri(id);
                } else {
                    throw new UnsupportedOperationException("Uri not supported table Agriculteurs " + uri);
                }
                break;
            case USER:
                id = db.insert(OcContract.Users.TABLE_NAME, null, contentValues);
                if (id > 0) {
                    returnUri = OcContract.Users.buildUserUri(id);
                } else {
                    throw new UnsupportedOperationException("Uri not supported table USERS  " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Not Implemented yet");

        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor returnCursor;
        final SQLiteDatabase db = mBfwDbHelper.getReadableDatabase();
        switch (mUriMatcher.match(uri)) {
            case AGRICULTEUR:
                returnCursor = db.query(OcContract.Agriculteurs.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case USER:
                returnCursor = db.query(OcContract.Users.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri desole = " + uri);
        }
        return returnCursor;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mBfwDbHelper.getWritableDatabase();
        int updateRow = 0;

        switch (mUriMatcher.match(uri)) {
            case AGRICULTEUR:
                updateRow = db.update(OcContract.Agriculteurs.TABLE_NAME, contentValues,
                        selection, selectionArgs);
                break;
            case USER:
                updateRow = db.update(OcContract.Users.TABLE_NAME, contentValues,
                        selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri");
        }
        if (updateRow != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updateRow;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mBfwDbHelper.getWritableDatabase();
        int deleteRow = 0;

        switch (mUriMatcher.match(uri)) {
            case AGRICULTEUR:
                deleteRow = db.delete(OcContract.Agriculteurs.TABLE_NAME,
                        selection, selectionArgs);
                break;
            case USER:
                deleteRow = db.delete(OcContract.Users.TABLE_NAME,
                        selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri");
        }
        if (deleteRow != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return deleteRow;
    }
}
