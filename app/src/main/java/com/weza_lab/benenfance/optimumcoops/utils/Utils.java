package com.weza_lab.benenfance.optimumcoops.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.weza_lab.benenfance.optimumcoops.R;

public class Utils {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /*public static String getUserType(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(context.getResources().getString(R.string.application_key),
                Context.MODE_PRIVATE);
        return prefs.getString(context.getResources().getString(R.string.g_name), "123");
    }*/

    public static int getUserType(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(context.getResources().getString(R.string.application_key),
                Context.MODE_PRIVATE);
        return prefs.getInt(context.getResources().getString(R.string.g_name), 123);
    }

    public static int getAgriServerId(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(context.getResources().getString(R.string.application_key),
                Context.MODE_PRIVATE);
        return prefs.getInt(context.getResources().getString(R.string.agri_id), 0);
    }

    public static int getDealerServerId(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(context.getResources().getString(R.string.application_key),
                Context.MODE_PRIVATE);
        return prefs.getInt(context.getResources().getString(R.string.dealer_id), 0);
    }

    public static int getVendeurServerId(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(context.getResources().getString(R.string.application_key),
                Context.MODE_PRIVATE);
        return prefs.getInt(context.getResources().getString(R.string.vendeur_id), 0);
    }

    public static int getGroupServerId(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(context.getResources().getString(R.string.application_key),
                Context.MODE_PRIVATE);
        return prefs.getInt(context.getResources().getString(R.string.group_id), 0);
    }

    public static boolean checkAlreadyLogin(Context context) {
        SharedPreferences prefsGoog = context
                .getSharedPreferences(context.getResources().getString(R.string.application_key), Context.MODE_PRIVATE);

        return prefsGoog.getBoolean(context.getResources().getString(R.string.app_id), false);
    }

    public static boolean checkAlreadyInstall(Context context) {
        SharedPreferences prefsGoog = context
                .getSharedPreferences(context.getResources().getString(R.string.application_key), Context.MODE_PRIVATE);

        return prefsGoog.getBoolean(context.getResources().getString(R.string.app_id), false);
    }
}
