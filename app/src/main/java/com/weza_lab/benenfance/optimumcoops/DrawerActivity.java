package com.weza_lab.benenfance.optimumcoops;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.weza_lab.benenfance.optimumcoops.utils.Utils;

import io.fabric.sdk.android.Fabric;


public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView navigationView;

    private TextView mUsernameView;
    private TextView mPhoneView;
    private ImageView mImageView;
    private int groupName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!BuildConfig.DEBUG) {
            Fabric.with(this, new Crashlytics());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, 0, 0);
        mDrawer.setDrawerListener(mDrawerToggle);

        navigationView = (NavigationView) findViewById(R.id.left_drawer2);
        navigationView.setNavigationItemSelectedListener(this);


        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    public void setUpNavigationView() {
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
            View header = LayoutInflater.from(this).inflate(R.layout.nav_header_pricipal, null);
            navigationView.addHeaderView(header);

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, 0, 0);

            mDrawer.addDrawerListener(toggle);
            SharedPreferences prefs = getApplicationContext().getSharedPreferences(getResources().getString(R.string.application_key),
                    Context.MODE_PRIVATE);
            String uName = prefs.getString(getResources().getString(R.string.user_name), "123");
            String uPhone = prefs.getString(getResources().getString(R.string.user_phone), "123");

            int n = Utils.getUserType(this);

            mUsernameView = header.findViewById(R.id.name_header);
            mUsernameView.setText(uName);
            mPhoneView = header.findViewById(R.id.phone_header);
            mPhoneView.setText(uPhone);
            mImageView = header.findViewById(R.id.imageView_header);
            mImageView.setImageResource(R.drawable.logo4);

            prefs = getApplicationContext().getSharedPreferences(getResources().getString(R.string.application_key),
                    Context.MODE_PRIVATE);
            groupName = prefs.getInt(getResources().getString(R.string.g_name), 123);

            if (groupName == 99) {
                // remove some item
                Menu menu = navigationView.getMenu();

            } else {
                // remove some item
                Menu menu = navigationView.getMenu();
                //menu.removeItem(R.id.credit);
            }
            toggle.syncState();
        }
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // mDrawerToggle.syncState();
        setUpNavigationView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return mDrawerToggle.onOptionsItemSelected(item) ||
        //  super.onOptionsItemSelected(item);
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return mDrawerToggle.onOptionsItemSelected(item) ||
                super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();
        Toast.makeText(this, "Clic  0", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Clic  0", Toast.LENGTH_SHORT).show();


        if (id == R.id.home) {
            // Handle the camera action
            Toast.makeText(this, "Clic  1", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.credit) {
            Toast.makeText(getApplicationContext(), "Clic  2", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.cafe) {
            Toast.makeText(this, "Clic  3", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.parametre) {
            Toast.makeText(this, "Clic  4", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.apropos) {

        } else if (id == R.id.deconexion) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
        //return false;
    }

}
