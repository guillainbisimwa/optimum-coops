package com.weza_lab.benenfance.optimumcoops.agri.inscription_agri;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.rd.PageIndicatorView;
import com.weza_lab.benenfance.optimumcoops.R;

import java.util.ArrayList;

public class Inscription_Agri2 extends AppCompatActivity implements View.OnClickListener {
    LoopingViewPager viewPager;
    InfiniteAdapter2 adapter;
    PageIndicatorView indicatorView;

    TextView changePageLabel;
    Button next;
    Button save;
    Button prev;

    private int currentDataSet = 0;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addagri2);

       /* Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(R.color.transparent);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ajouter agriculteur");

        getSupportActionBar().setElevation(0.0f);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        //getSupportActionBar().set

        viewPager = findViewById(R.id.viewpager2);
        indicatorView = findViewById(R.id.indicator);

        changePageLabel = findViewById(R.id.title_incr);
        next = findViewById(R.id.button_next);
        save = findViewById(R.id.button_save);
        prev = findViewById(R.id.button_prev);

        adapter = new InfiniteAdapter2(this, createDummyItems(), false);
        viewPager.setAdapter(adapter);
        //viewPager.setCurrentItem(2);

        //Custom bind indicator
        indicatorView.setCount(viewPager.getIndicatorCount());
        Toast.makeText(this, "Btrr " + viewPager.getCurrentItem(), Toast.LENGTH_LONG).show();
        viewPager.setIndicatorPageChangeListener(new LoopingViewPager.IndicatorPageChangeListener() {
            @Override
            public void onIndicatorProgress(int selectingPosition, float progress) {
                indicatorView.setProgress(selectingPosition, progress);
            }

            @Override
            public void onIndicatorPageChange(int newIndicatorPosition) {
                indicatorView.setSelection(newIndicatorPosition);
            }
        });

        View.OnClickListener pageSelector = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Integer number = Integer.valueOf(((Button)v).getText().toString());
                //viewPager.setCurrentItem(adapter.isInfinite() ? number : number - 1);
                viewPager.setCurrentItem(2);

                //viewPager.getCurrentItem();
            }
        };

        next.setOnClickListener(pageSelector);
        //save.setOnClickListener(pageSelector);
        //prev.setOnClickListener(pageSelector);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //viewPager.resumeAutoScroll();
    }

    @Override
    protected void onPause() {
        //viewPager.pauseAutoScroll();
        super.onPause();
    }

    private ArrayList<Integer> createDummyItems() {
        ArrayList<Integer> items = new ArrayList<>();
        items.add(0, 1);
        items.add(1, 2);
        items.add(2, 3);
        return items;
    }


    private void toggleSixButtons(boolean isVisible) {
        int status = isVisible ? View.VISIBLE : View.GONE;
        changePageLabel.setVisibility(status);
        next.setVisibility(status);
        save.setVisibility(status);
        prev.setVisibility(status);
    }

    @Override
    public void onClick(View view) {
    }
}
