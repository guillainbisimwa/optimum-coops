package com.weza_lab.benenfance.optimumcoops.fragment;

import android.content.Context;
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
import com.weza_lab.benenfance.optimumcoops.adapter.PersRecyclerViewAdapter;
import com.weza_lab.benenfance.optimumcoops.database.DBHelper;
import com.weza_lab.benenfance.optimumcoops.database.DBQueries;
import com.weza_lab.benenfance.optimumcoops.pojo.Personnes;

import java.util.List;

import butterknife.ButterKnife;

public class PersRecyclerViewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 3;

    DBQueries dbQueries;
    DBHelper dbHelper;

    Personnes personnes;

    //@BindView(R.id.recyclerView)
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private CoordinatorLayout coordinatorLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View emptyView;

    private List<Personnes> items;

    public static PersRecyclerViewFragment newInstance() {
        return new PersRecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dbQueries = new DBQueries(getContext());
        dbHelper = new DBHelper(getContext());

        View view = inflater.inflate(R.layout.fragment_recyclerview_ref, container, false);
        emptyView = view.findViewById(R.id.recyclerview_empty);
        Context context = view.getContext();
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

        personnes = new Personnes();


        //swipeRefreshLayout.setOnRefreshListener(this);


        dbQueries.open();
        items = dbQueries.readPersonnes();
        dbQueries.close();

        //setup materialviewpager
        swipeRefreshLayout.setOnRefreshListener(this);
        if (!items.isEmpty()) {
            //Toast.makeText(getContext(), items.size() + " Utilisteur(s)", Toast.LENGTH_LONG).show();
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
        mRecyclerView.setAdapter(new PersRecyclerViewAdapter(items, getContext()));


    }

    @Override
    public void onRefresh() {
        Toast.makeText(getContext(), getResources().getString(R.string.connectivity_error), Toast.LENGTH_LONG).show();
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {

                dbQueries.open();
                items = dbQueries.readPersonnes();
                dbQueries.close();

                mRecyclerView.setAdapter(new PersRecyclerViewAdapter(items, getContext()));
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
