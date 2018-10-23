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
import com.weza_lab.benenfance.optimumcoops.agri.EditAgriculteurs;
import com.weza_lab.benenfance.optimumcoops.agri.addAgriculteurs;
import com.weza_lab.benenfance.optimumcoops.agri.details_agri.DetailsAgriRecyclerViewFrgmnt;
import com.weza_lab.benenfance.optimumcoops.credit.Main_credit;
import com.weza_lab.benenfance.optimumcoops.fragment.AchRecyclerViewFragment;
import com.weza_lab.benenfance.optimumcoops.fragment.CoopRecyclerViewFragment;
import com.weza_lab.benenfance.optimumcoops.fragment.DealRecyclerViewFragment;
import com.weza_lab.benenfance.optimumcoops.fragment.PersRecyclerViewFragment;
import com.weza_lab.benenfance.optimumcoops.fragment.RecyclerViewFragment;
import com.weza_lab.benenfance.optimumcoops.group.Add_group;
import com.weza_lab.benenfance.optimumcoops.group.fragment.GroupRecyclerViewFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends DrawerActivity {

    @BindView(R.id.materialViewPager)
    MaterialViewPager mViewPager;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    private String mPhone, uName;
    private int user_categorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //Categorize all users
        SharedPreferences prefs = getApplicationContext().getSharedPreferences(getResources().getString(R.string.application_key),
                Context.MODE_PRIVATE);
        user_categorie = prefs.getInt(getResources().getString(R.string.g_name), 99);
        mPhone = prefs.getString(getResources().getString(R.string.user_phone), "");
        uName = prefs.getString(getResources().getString(R.string.user_name), "");

        //if (user_categorie.equals("Admin"))
        // {
        setContentView(R.layout.drawer2);
        //}
        //else if(user_categorie.equals("Agriculteurs"))
        //{
        //  setContentView(R.layout.drawer_edit);
        //}

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
                if (user_categorie == 99) {
                    switch (position % 5) {
                        case 0:
                            return PersRecyclerViewFragment.newInstance();
                        case 1:
                            return GroupRecyclerViewFragment.newInstance();
                        case 2:
                            return CoopRecyclerViewFragment.newInstance();
                        case 3:
                            return AchRecyclerViewFragment.newInstance();
                        case 4:
                            return DealRecyclerViewFragment.newInstance();
                        default:
                            return RecyclerViewFragment.newInstance();
                    }
                } else if ((user_categorie == 100) || (user_categorie == 101) || (user_categorie == 102) || ((user_categorie == 103))) {
                    //agri
                    switch (position % 2) {
                        case 0:
                            Fragment f = new DetailsAgriRecyclerViewFrgmnt();
                            Bundle bundle = new Bundle();
                            bundle.putString("mPhone", mPhone);

                            f.setArguments(bundle);

                            return f;
                        case 1:
                            return GroupRecyclerViewFragment.newInstance();

                    }

                }
                return null;
            }

            @Override
            public int getCount() {

                if (user_categorie == 99) {
                    return 5;
                } else if ((user_categorie == 100) || (user_categorie == 101) || (user_categorie == 102) || ((user_categorie == 103))) {
                    return 2;
                }
                return 0;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (user_categorie == 99) {
                    switch (position % 5) {
                        case 0:
                            return getString(R.string.utilisateurs);
                        case 1:
                            return getString(R.string.groupes);
                        case 2:
                            return getString(R.string.cooperatives);
                        case 3:
                            return getString(R.string.acheteur);
                        case 4:
                            return getString(R.string.dealers);
                    }
                } else if ((user_categorie == 100) || (user_categorie == 101) || (user_categorie == 102) || ((user_categorie == 103))) {
                    switch (position % 5) {
                        case 0:
                            return uName + "(" + user_categorie + ")";
                        //return getString(R.string.agriculteur);
                        case 1:
                            return getString(R.string.groupe);
                    }
                }
                return "";
            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            //@SuppressLint("ResourceAsColor")
            @Override
            public HeaderDesign getHeaderDesign(int page) {

                if (user_categorie == 99) {
                    switch (page) {
                        case 0:
                            if (floatingActionButton != null) {
                                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mViewPager.notifyHeaderChanged();
                                        Intent i = new Intent(getBaseContext(), addAgriculteurs.class);
                                        //finish();
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
                                        startActivity(new Intent(getBaseContext(), Add_group.class));
                                        //Toast.makeText(getApplicationContext(), "Clic sur fab group", Toast.LENGTH_SHORT).show();
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
                } else if ((user_categorie == 100) || (user_categorie == 101) || (user_categorie == 102) || ((user_categorie == 103))) {
                    switch (page) {
                        case 0:
                            if (floatingActionButton != null) {
                                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mViewPager.notifyHeaderChanged();
                                        Intent i = new Intent(getBaseContext(), EditAgriculteurs.class);
                                        i.putExtra("mPhone", mPhone);
                                        //finish();
                                        startActivity(i);
                                        //finish();
                                        //Toast.makeText(getApplicationContext(), "Clic sur fab edit", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            return HeaderDesign.fromColorResAndDrawable(
                                    R.color.green, getResources().getDrawable(R.drawable.agri), getResources().getDrawable(R.drawable.ic_create_black_24dp));
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
                    }

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
            Intent i = new Intent(this, Main_credit.class);
            i.putExtra("mPhone", mPhone);
            i.putExtra("mName", uName);
            i.putExtra("mType", user_categorie);
            this.startActivity(i);

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
