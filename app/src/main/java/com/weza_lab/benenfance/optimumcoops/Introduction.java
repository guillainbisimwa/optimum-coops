package com.weza_lab.benenfance.optimumcoops;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

public class Introduction extends AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getApplicationContext().getSharedPreferences(getResources().getString(R.string.application_key),
                Context.MODE_PRIVATE);
        boolean installed = prefs.getBoolean(getResources().getString(R.string.install_app_id), false);
        boolean connected = prefs.getBoolean(getResources().getString(R.string.app_id), true);

        if (installed) {
            finish();
            startActivity(new Intent(getApplicationContext(), Spash.class));
        }

        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard(getString(R.string.item1_titre), getString(R.string.item1_content), R.drawable.logo4);
        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard1.setTitleColor(R.color.white);
        ahoyOnboarderCard1.setDescriptionColor(R.color.grey_200);
        ahoyOnboarderCard1.setTitleTextSize(dpToPixels(10, this));
        ahoyOnboarderCard1.setDescriptionTextSize(dpToPixels(8, this));
        //ahoyOnboarderCard1.setIconLayoutParams(300, 300, 20, 20, 20, 20);

        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard(getString(R.string.item2_titre), getString(R.string.item2_content), R.drawable.treeeround);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard2.setTitleColor(R.color.white);
        ahoyOnboarderCard2.setDescriptionColor(R.color.grey_200);
        ahoyOnboarderCard2.setTitleTextSize(dpToPixels(10, this));
        ahoyOnboarderCard2.setDescriptionTextSize(dpToPixels(8, this));
        //ahoyOnboarderCard2.setIconLayoutParams(300, 300, 20, 20, 20, 20);

        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard(getString(R.string.item3_titre), getString(R.string.item3_content), R.drawable.ratio);
        ahoyOnboarderCard3.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard3.setTitleColor(R.color.white);
        ahoyOnboarderCard3.setDescriptionColor(R.color.grey_200);
        ahoyOnboarderCard3.setTitleTextSize(dpToPixels(10, this));
        ahoyOnboarderCard3.setDescriptionTextSize(dpToPixels(8, this));
        //ahoyOnboarderCard3.setIconLayoutParams(300, 300, 20, 20, 20, 20);


        List<AhoyOnboarderCard> pages = new ArrayList<>();
        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);

        setFinishButtonTitle("Commencer");
        showNavigationControls(true);
        setGradientBackground();

        //set the button style you created
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setFinishButtonDrawableStyle(ContextCompat.getDrawable(this, R.drawable.rounded_button));
        }

        setOnboardPages(pages);
    }


    @Override
    public void onFinishButtonPressed() {

        //nOT ALLOW INTALLATION AFTER REACH THIS ui FOR A FIRST TIME
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(getResources().getString(R.string.application_key),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor_install = preferences.edit();
        editor_install.putBoolean(getResources().getString(R.string.install_app_id), true);
        editor_install.apply();

        startActivity(new Intent(getApplicationContext(), Spash.class));
        finish();

    }

    ;
}
