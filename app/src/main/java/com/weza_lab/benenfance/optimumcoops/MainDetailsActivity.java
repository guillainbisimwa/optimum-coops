package com.weza_lab.benenfance.optimumcoops;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.weza_lab.benenfance.optimumcoops.fragment.RecyclerViewFragment;
import com.weza_lab.benenfance.optimumcoops.group.fragment.GroupRecyclerViewFragment;
import com.weza_lab.benenfance.optimumcoops.personne.addAgriculteurs;
import com.weza_lab.benenfance.optimumcoops.personne.details_agri.DetailsAgriRecyclerViewFrgmnt;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainDetailsActivity extends DrawerActivity {

    @BindView(R.id.materialViewPager)
    MaterialViewPager mViewPager;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    private String mPhone, uName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.drawer2);

        setTitle("");
        ButterKnife.bind(this);

        final Toolbar toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }


        floatingActionButton = findViewById(R.id.fab);
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 2) {
                    case 0: {
                        Fragment f = new DetailsAgriRecyclerViewFrgmnt();
                        Bundle bundle = new Bundle();
                        bundle.putString("mPhone", mPhone);

                        f.setArguments(bundle);

                        return f;
                    }

                    case 1:
                        return GroupRecyclerViewFragment.newInstance();

                    default:
                        return RecyclerViewFragment.newInstance();

                }

            }

            @Override
            public int getCount() {

                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {

                switch (position % 2) {
                    case 0:
                        return getString(R.string.utilisateurs);
                    case 1:
                        return getString(R.string.groupes);
                }
                return "";
            }

        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            //@SuppressLint("ResourceAsColor")
            @Override
            public HeaderDesign getHeaderDesign(int page) {

                switch (page) {
                    case 0:
                        if (floatingActionButton != null) {
                            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mViewPager.notifyHeaderChanged();
                                    Intent i = new Intent(getBaseContext(), addAgriculteurs.class);
                                    startActivity(i);
                                    //Toast.makeText(getApplicationContext(), "Clic sur fab agriculteur", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.green, getResources().getDrawable(R.drawable.agri), getResources().getDrawable(R.drawable.ic_person_add_black_24dp));
                    //"http://phandroid.s3.amazonaws.com/wp-content/uploads/2014/06/android_google_moutain_google_now_1920x1080_wallpaper_Wallpaper-HD_2560x1600_www.paperhi.com_-640x400.jpg");
                    case 1:
                        if (floatingActionButton != null) {
                            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mViewPager.notifyHeaderChanged();
                                    Toast.makeText(getApplicationContext(), "Clic sur fab group", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.blue, getResources().getDrawable(R.drawable.group), getResources().getDrawable(R.drawable.ic_group_add_black_24dp));
                    case 2:
                        if (floatingActionButton != null) {
                            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mViewPager.notifyHeaderChanged();
                                    Toast.makeText(getApplicationContext(), "Clic sur fab cooperative", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.cyan, getResources().getDrawable(R.drawable.cooperartive), getResources().getDrawable(R.drawable.ic_add_black_24dp));
                    case 3:
                        if (floatingActionButton != null) {
                            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mViewPager.notifyHeaderChanged();
                                    Toast.makeText(getApplicationContext(), "Clic sur fab acheteur", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.red, getResources().getDrawable(R.drawable.acheteur), getResources().getDrawable(R.drawable.ic_add_black_24dp));
                    case 4:
                        if (floatingActionButton != null) {
                            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mViewPager.notifyHeaderChanged();
                                    Toast.makeText(getApplicationContext(), "Clic sur fab dealers", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.lime, getResources().getDrawable(R.drawable.pic3), getResources().getDrawable(R.drawable.ic_add_black_24dp));
                }


                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        final View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), "Clic sur le titre", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();
        if (id == R.id.home) {
            // Handle actions
            Toast.makeText(this, "Clic  Home", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.credit) {
            Toast.makeText(getApplicationContext(), "Clic  credit", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.cafe) {
            Toast.makeText(this, "Clic  cafe", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.parametre) {
            Toast.makeText(this, "Clic  parametre", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.apropos) {
            Toast.makeText(this, "Clic  apropos", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.deconexion) {
            //Toast.makeText(this, "Clic  deconnexion", Toast.LENGTH_SHORT).show();

            SharedPreferences preferences = getApplicationContext().getSharedPreferences(getResources().getString(R.string.application_key),
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor_connect = preferences.edit();
            editor_connect.putBoolean(getResources().getString(R.string.app_id), false);
            editor_connect.apply();
            finish();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
