package com.weza_lab.benenfance.optimumcoops.group.fragment;

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
import com.weza_lab.benenfance.optimumcoops.database.DBHelper;
import com.weza_lab.benenfance.optimumcoops.database.DBQueries;
import com.weza_lab.benenfance.optimumcoops.group.adaptater.GroupRecyclerViewAdapter;
import com.weza_lab.benenfance.optimumcoops.pojo.Groups;
import com.weza_lab.benenfance.optimumcoops.pojo.Personnes;

import java.util.List;

import butterknife.ButterKnife;

public class GroupRecyclerViewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final boolean GRID_LAYOUT = true;

    //@BindView(R.id.recyclerView)
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private CoordinatorLayout coordinatorLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View emptyView;

    private DBQueries dbQueries;
    private DBHelper dbHelper;

    private Groups groups;
    private List<Groups> items;
    private List<Personnes> items_personnes;


    public static GroupRecyclerViewFragment newInstance() {
        return new GroupRecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview_ref, container, false);
        emptyView = view.findViewById(R.id.recyclerview_empty);
        Context context = view.getContext();
        mRecyclerView = view.findViewById(R.id.recyclerView);

        dbQueries = new DBQueries(getContext());
        dbHelper = new DBHelper(getContext());

        coordinatorLayout = view.findViewById(R.id.coordinator);
        swipeRefreshLayout = view.findViewById(R.id.refresh);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        groups = new Groups();
        dbQueries.open();
        //items = dbQueries.readGroup();
        items = dbQueries.readGroupWithNbr();
        dbQueries.close();

        swipeRefreshLayout.setOnRefreshListener(this);
        if (!items.isEmpty()) {
            //Toast.makeText(getContext(), items.size() + " Groupe(s)", Toast.LENGTH_LONG).show();
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

        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(new GroupRecyclerViewAdapter(items, getContext()));
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getContext(), getResources().getString(R.string.connectivity_error), Toast.LENGTH_LONG).show();
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                dbQueries.open();
                //items = dbQueries.readGroup();
                items = dbQueries.readGroupWithNbr();
                dbQueries.close();

                if (!items.isEmpty()) {
                    emptyView.setVisibility(View.INVISIBLE);
                } else {
                    emptyView.setVisibility(View.VISIBLE);
                }

                mRecyclerView.setAdapter(new GroupRecyclerViewAdapter(items, getContext()));
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
