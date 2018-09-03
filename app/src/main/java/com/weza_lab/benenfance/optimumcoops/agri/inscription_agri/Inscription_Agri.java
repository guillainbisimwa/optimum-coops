package com.weza_lab.benenfance.optimumcoops.agri.inscription_agri;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.rd.PageIndicatorView;
import com.weza_lab.benenfance.optimumcoops.R;

import java.util.ArrayList;

public class Inscription_Agri extends AppCompatActivity implements View.OnClickListener {
    LoopingViewPager viewPager;
    InfiniteAdapter adapter;
    //PageIndicatorView indicatorView;
    PageIndicatorView indicatorView;
    Button changeDataSetButton;

    TextView changePageLabel;
    Button page1;
    Button page2;
    Button page3;
    Button page4;
    Button page5;
    Button page6;

    private int currentDataSet = 1;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addagri);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(R.color.transparent);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ajouter agriculteur");

        getSupportActionBar().setElevation(0.0f);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().set

        viewPager = findViewById(R.id.viewpager);
        indicatorView = findViewById(R.id.indicator);
        changeDataSetButton = findViewById(R.id.change_dataset);
        try {
            changeDataSetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeDataset();
                }
            });
        } catch (Exception e) {

        }
        //indicatorView.setOnClickListener(this);
        changePageLabel = findViewById(R.id.change_page_label);
        page1 = findViewById(R.id.page1);
        page2 = findViewById(R.id.page2);
        page3 = findViewById(R.id.page3);
        page4 = findViewById(R.id.page4);
        page5 = findViewById(R.id.page5);
        page6 = findViewById(R.id.page6);

        adapter = new InfiniteAdapter(this, createDummyItems(), true);
        viewPager.setAdapter(adapter);

        //Custom bind indicator
        indicatorView.setCount(viewPager.getIndicatorCount());
        viewPager.setIndicatorPageChangeListener(new LoopingViewPager.IndicatorPageChangeListener() {
            @Override
            public void onIndicatorProgress(int selectingPosition, float progress) {
                indicatorView.setProgress(selectingPosition, progress);
            }

            @Override
            public void onIndicatorPageChange(int newIndicatorPosition) {
//                indicatorView.setSelection(newIndicatorPosition);
            }
        });

        View.OnClickListener pageSelector = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer number = Integer.valueOf(((Button) v).getText().toString());
                viewPager.setCurrentItem(adapter.isInfinite() ? number : number - 1);
            }
        };

        page1.setOnClickListener(pageSelector);
        page2.setOnClickListener(pageSelector);
        page3.setOnClickListener(pageSelector);
        page4.setOnClickListener(pageSelector);
        page5.setOnClickListener(pageSelector);
        page6.setOnClickListener(pageSelector);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.resumeAutoScroll();
    }

    @Override
    protected void onPause() {
        viewPager.pauseAutoScroll();
        super.onPause();
    }

    private ArrayList<Integer> createDummyItems() {
        ArrayList<Integer> items = new ArrayList<>();
        items.add(0, 1);
        items.add(1, 2);
        items.add(2, 3);
        items.add(3, 4);
        items.add(4, 5);
        items.add(5, 6);
        items.add(6, 0);
        return items;
    }

    private ArrayList<Integer> createSecondDummyItems() {
        ArrayList<Integer> items = new ArrayList<>();
        items.add(0, 1);
        items.add(1, 2);
        return items;
    }

    private ArrayList<Integer> createThirdDummyItems() {
        ArrayList<Integer> items = new ArrayList<>();
        items.add(0, 1);
        return items;
    }

    private void changeDataset() {
        if (currentDataSet == 1) {
            adapter.setItemList(createSecondDummyItems());
            currentDataSet++;
            toggleSixButtons(false);
        } else if (currentDataSet == 2) {
            adapter.setItemList(createThirdDummyItems());
            currentDataSet++;
            toggleSixButtons(false);
        } else {
            adapter.setItemList(createDummyItems());
            currentDataSet = 1;
            toggleSixButtons(true);
        }
        indicatorView.setCount(viewPager.getIndicatorCount());
        viewPager.reset();
    }

    private void toggleSixButtons(boolean isVisible) {
        int status = isVisible ? View.VISIBLE : View.GONE;
        changePageLabel.setVisibility(status);
        page1.setVisibility(status);
        page2.setVisibility(status);
        page3.setVisibility(status);
        page4.setVisibility(status);
        page5.setVisibility(status);
        page6.setVisibility(status);
    }

    @Override
    public void onClick(View view) {
        //changeDataset();
    }
}
