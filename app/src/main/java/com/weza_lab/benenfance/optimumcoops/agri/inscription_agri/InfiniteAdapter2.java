package com.weza_lab.benenfance.optimumcoops.agri.inscription_agri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.weza_lab.benenfance.optimumcoops.R;

import java.util.ArrayList;

public class InfiniteAdapter2 extends LoopingPagerAdapter<Integer> {

    private static final int VIEW_IDENTITE = 100;
    private static final int VIEW_LOCALISATION = 101;
    private static final int VIEW_INFO = 102;

    public InfiniteAdapter2(Context context, ArrayList<Integer> itemList, boolean isInfinite) {
        super(context, itemList, isInfinite);
    }

    @Override
    protected int getItemViewType(int listPosition) {
        if (itemList.get(listPosition) == 0) return VIEW_IDENTITE;
        else if (itemList.get(listPosition) == 1) return VIEW_LOCALISATION;
        else if (itemList.get(listPosition) == 2) return VIEW_INFO;
        //else return VIEW_INFO;
        return 0;
    }

    @Override
    protected View inflateView(int viewType, ViewGroup container, int listPosition) {
/*
        if (viewType == VIEW_IDENTITE)
            return LayoutInflater.from(context).inflate(R.layout.item_special, container, false);
        else if (viewType == VIEW_LOCALISATION)
            return LayoutInflater.from(context).inflate(R.layout.item_special, container, false);
        else*/
        return LayoutInflater.from(context).inflate(R.layout.item_special, container, false);
    }

    @Override
    protected void bindView(View convertView, int listPosition, int viewType) {
        if (viewType == VIEW_IDENTITE) {
            //convertView.findViewById(R.id.image).setBackgroundColor(context.getResources().getColor(getBackgroundColor(listPosition)));
            convertView.findViewById(R.id.identite_inscr_agri).setVisibility(View.VISIBLE);
            convertView.findViewById(R.id.localisation_inscr_agri).setVisibility(View.GONE);

        } else if (viewType == VIEW_LOCALISATION) {
            //convertView.findViewById(R.id.image).setBackgroundColor(context.getResources().getColor(getBackgroundColor(listPosition)));
            convertView.findViewById(R.id.localisation_inscr_agri).setVisibility(View.VISIBLE);
            convertView.findViewById(R.id.identite_inscr_agri).setVisibility(View.GONE);
        } else if (viewType == VIEW_INFO) {

            //convertView.findViewById(R.id.image).setBackgroundColor(context.getResources().getColor(getBackgroundColor(listPosition)));
            convertView.findViewById(R.id.identite_inscr_agri).setVisibility(View.VISIBLE);
            convertView.findViewById(R.id.localisation_inscr_agri).setVisibility(View.VISIBLE);
        }
        //else return;

    }


    private int getBackgroundColor(int number) {
        return android.R.color.transparent;
    }
}
