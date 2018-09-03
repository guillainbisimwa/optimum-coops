package com.weza_lab.benenfance.optimumcoops.fragment;

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
import com.weza_lab.benenfance.optimumcoops.PersRecyclerViewAdapter;
import com.weza_lab.benenfance.optimumcoops.R;
import com.weza_lab.benenfance.optimumcoops.database.DBHelper;
import com.weza_lab.benenfance.optimumcoops.database.DBQueries;
import com.weza_lab.benenfance.optimumcoops.pojo.Personnes;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersRecyclerViewFragment extends Fragment {

    private static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 3;

    DBQueries dbQueries;
    DBHelper dbHelper;

    Personnes personnes;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public static PersRecyclerViewFragment newInstance() {
        return new PersRecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dbQueries = new DBQueries(getContext());
        dbHelper = new DBHelper(getContext());
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        personnes = new Personnes();
        List<Personnes> items;

        dbQueries.open();
        items = dbQueries.readPersonnes();
        dbQueries.close();

        //setup materialviewpager
        if (items.isEmpty()) {
            Toast.makeText(getContext(), items.size() + " utilisateur(s)", Toast.LENGTH_LONG).show();
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
        mRecyclerView.setAdapter(new PersRecyclerViewAdapter(items, getContext()));
    }
}
