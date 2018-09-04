package com.weza_lab.benenfance.optimumcoops.agri.details_agri;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.weza_lab.benenfance.optimumcoops.R;
import com.weza_lab.benenfance.optimumcoops.agri.EditAgriculteurs;
import com.weza_lab.benenfance.optimumcoops.fragment.RecyclerViewFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Details_agri extends AppCompatActivity {

    @BindView(R.id.materialViewPager)
    MaterialViewPager mViewPager;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    private String mPhone, mName;
    private int mType;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.drawer_edit);
        setTitle("");
        ButterKnife.bind(this);

        //Get extra from intent. get phone number
        Intent intent = this.getIntent();
        if (intent.hasExtra("mPhone")) {
            mPhone = intent.getStringExtra("mPhone");
        } else mPhone = "";
        if (intent.hasExtra("mName")) {
            mName = intent.getStringExtra("mName");
        } else mName = "";

        if (intent.hasExtra("mType")) {
            mType = intent.getIntExtra("mType", 0);
        } else mType = 0;

        final Toolbar toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        }

        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setImageResource(R.drawable.ic_create_black_24dp);
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 1) {
                    case 0: {
                        Fragment f = new DetailsAgriRecyclerViewFrgmnt();
                        Bundle bundle = new Bundle();
                        bundle.putString("mPhone", mPhone);

                        f.setArguments(bundle);

                        return f;
                        //return DetailsAgriRecyclerViewFrgmnt.newInstance();
                    }
                    default:
                        return RecyclerViewFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 1;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 1) {
                    case 0:
                        //return getString(R.string.agriculteurs);
                        return mName;
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
                                    if (mType == 100) {
                                        Intent i = new Intent(getBaseContext(), EditAgriculteurs.class);
                                        i.putExtra("mPhone", mPhone);
                                        startActivity(i);
                                    } else
                                        Toast.makeText(getApplicationContext(), "Clic sur fab edit", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.green, getResources().getDrawable(R.drawable.agri), getResources().getDrawable(R.drawable.ic_create_black_24dp));
                    //"http://phandroid.s3.amazonaws.com/wp-content/uploads/2014/06/android_google_moutain_google_now_1920x1080_wallpaper_Wallpaper-HD_2560x1600_www.paperhi.com_-640x400.jpg");
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
                }
            });
        }
    }

}
