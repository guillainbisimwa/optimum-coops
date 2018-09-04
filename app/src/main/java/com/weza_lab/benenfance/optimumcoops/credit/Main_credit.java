package com.weza_lab.benenfance.optimumcoops.credit;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.weza_lab.benenfance.optimumcoops.R;
import com.weza_lab.benenfance.optimumcoops.credit.fragment.CreditDialogFragment;
import com.weza_lab.benenfance.optimumcoops.credit.fragment.CreditListRecyclerViewFragment;
import com.weza_lab.benenfance.optimumcoops.fragment.RecyclerViewFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main_credit extends AppCompatActivity {

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
        setContentView(R.layout.drawer_credit);
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
        floatingActionButton.setImageResource(R.drawable.ic_add_black_24dp);

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                if ((mType == 99) || (mType == 100) || (mType == 101) || (mType == 102) || ((mType == 103))) {
                    switch (position % 1) {
                        case 0: {
                            Fragment f = new CreditListRecyclerViewFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("mPhone", mPhone);
                            bundle.putInt("mType", mType);

                            f.setArguments(bundle);

                            return f;
                        }
                        default:
                            return RecyclerViewFragment.newInstance();
                    }
                }

                return null;
            }

            @Override
            public int getCount() {
                return 1;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (mType == 99) {
                    switch (position % 1) {
                        case 0:
                            return getString(R.string.credits);
                    }
                } else if ((mType == 100) || (mType == 101) || (mType == 102) || ((mType == 103))) {
                    switch (position % 1) {
                        case 0:
                            return getString(R.string.mes_credits);
                    }
                }

                return getString(R.string.mes_credits);
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
                                    mViewPager.notifyHeaderChanged();
                                    CreditDialogFragment dialogFragment = new CreditDialogFragment();

                                    dialogFragment.show(getSupportFragmentManager(), "CreditDialog");

                                    mViewPager.notifyHeaderChanged();

                                    /*if (mType == 100) {
                                        Toast.makeText(getApplicationContext(), "Clic sur credit add", Toast.LENGTH_SHORT).show();
                                    } else
                                    {

                                        CreditDialogFragment dialogFragment = new CreditDialogFragment();

                                        dialogFragment.show(getSupportFragmentManager(), "CreditDialog");

                                    }*/

                                }
                            });
                        }
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.green, getResources().getDrawable(R.drawable.agri), getResources().getDrawable(R.drawable.ic_add_black_24dp));
                    //"http://phandroid.s3.amazonaws.com/wp-content/uploads/2014/06/android_google_moutain_google_now_1920x1080_wallpaper_Wallpaper-HD_2560x1600_www.paperhi.com_-640x400.jpg");
                }
                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        final View logo = findViewById(R.id.img_logo);
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
