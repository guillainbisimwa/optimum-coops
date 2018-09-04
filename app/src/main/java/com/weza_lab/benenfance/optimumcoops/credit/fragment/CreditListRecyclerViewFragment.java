package com.weza_lab.benenfance.optimumcoops.credit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.weza_lab.benenfance.optimumcoops.R;
import com.weza_lab.benenfance.optimumcoops.credit.adaptater.CreditListRecyclerViewAdapter;
import com.weza_lab.benenfance.optimumcoops.database.DBHelper;
import com.weza_lab.benenfance.optimumcoops.database.DBQueries;
import com.weza_lab.benenfance.optimumcoops.pojo.Credit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreditListRecyclerViewFragment extends Fragment {

    private static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 3;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private DBQueries dbQueries;
    private DBHelper dbHelper;
    private Credit credit;
    private String mPhone;
    private int mType;

    public static CreditListRecyclerViewFragment newInstance() {
        return new CreditListRecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dbQueries = new DBQueries(getContext());
        dbHelper = new DBHelper(getContext());

        if (getArguments() != null) {
            mPhone = getArguments().getString("mPhone");
            mType = getArguments().getInt("mPhone");
        }

        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        credit = new Credit();
        List<Credit> items;

        //personnes = new Personnes();
        if (getArguments() != null) {
            mPhone = getArguments().getString("mPhone");
            mType = getArguments().getInt("mPhone");
        }
        if (mType == 99) {
            dbQueries.open();
            items = dbQueries.readCredits();
            dbQueries.close();
        } else {
            dbQueries.open();
            items = dbQueries.readOneCredits(mPhone);
            dbQueries.close();
        }


        //setup materialviewpager
        if (!items.isEmpty()) {
            Toast.makeText(getContext(), items.size() + " Credits(s)", Toast.LENGTH_LONG).show();
        }

        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setHasFixedSize(true);
        //Toast.makeText(getContext(),"ok",Toast.LENGTH_SHORT).show();
        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(new CreditListRecyclerViewAdapter(items, getContext()));
    }
}
