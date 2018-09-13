package com.weza_lab.benenfance.optimumcoops.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.weza_lab.benenfance.optimumcoops.R;
import com.weza_lab.benenfance.optimumcoops.adapter.AgriRecyclerViewAdapter;
import com.weza_lab.benenfance.optimumcoops.database.DBHelper;
import com.weza_lab.benenfance.optimumcoops.database.DBQueries;
import com.weza_lab.benenfance.optimumcoops.pojo.Agriculteurs;

import java.util.List;

import butterknife.ButterKnife;

public class AgriRecyclerViewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 3;

    DBQueries dbQueries;
    DBHelper dbHelper;

    Agriculteurs agriculteurs;

    //@BindView(R.id.recyclerView)
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private CoordinatorLayout coordinatorLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View emptyView;

    public static AgriRecyclerViewFragment newInstance() {
        return new AgriRecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_recyclerview, container, false);
        dbQueries = new DBQueries(getContext());
        dbHelper = new DBHelper(getContext());

        View view = inflater.inflate(R.layout.fragment_recyclerview_ref, container, false);
        emptyView = view.findViewById(R.id.recyclerview_empty);
        mRecyclerView = view.findViewById(R.id.recyclerView);


        coordinatorLayout = view.findViewById(R.id.coordinator);
        swipeRefreshLayout = view.findViewById(R.id.refresh);

        //return inflater.inflate(R.layout.fragment_recyclerview, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        agriculteurs = new Agriculteurs();
        List<Agriculteurs> items;

        dbQueries.open();
        items = dbQueries.readAgriculteurs();
        dbQueries.close();

        //setup materialviewpager
        if (items.isEmpty()) {
            //Toast.makeText(getContext(), "ok " + items.size(), Toast.LENGTH_LONG).show();
        }

        //ecouteyr
        swipeRefreshLayout.setOnRefreshListener(this);
        if (!items.isEmpty()) {
            Toast.makeText(getContext(), items.size() + " Agriculteurs(s)", Toast.LENGTH_LONG).show();
            emptyView.setVisibility(View.INVISIBLE);
        } else {
            emptyView.setVisibility(View.VISIBLE);
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
        mRecyclerView.setAdapter(new AgriRecyclerViewAdapter(items, getContext()));
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getContext(), getResources().getString(R.string.connectivity_error), Toast.LENGTH_LONG).show();
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                /*if (mType == 99) {
                    dbQueries.open();
                    items = dbQueries.readCredits();
                    dbQueries.close();
                } else {
                    dbQueries.open();
                    items = dbQueries.readOneCredits(mPhone);
                    dbQueries.close();
                }
                mRecyclerView.setAdapter(new AgriRecyclerViewAdapter(items, getContext()));*/
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
