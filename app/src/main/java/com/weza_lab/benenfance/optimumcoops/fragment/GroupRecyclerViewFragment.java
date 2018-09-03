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

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.weza_lab.benenfance.optimumcoops.GroupRecyclerViewAdapter;
import com.weza_lab.benenfance.optimumcoops.R;
import com.weza_lab.benenfance.optimumcoops.pojo.Groupes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class GroupRecyclerViewFragment extends Fragment {

    private static final boolean GRID_LAYOUT = true;
    private static final int ITEM_COUNT = 15;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public static GroupRecyclerViewFragment newInstance() {
        return new GroupRecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        final List<Groupes> items = new ArrayList<>();

        items.add(new Groupes(1, "Groupe 1", "MUMOSHO 1"));
        /*items.add(new Groupes(2,"Groupe 2","MASISI 1"));
        items.add(new Groupes(3,"Groupe 3","WALOKALE 1"));
        items.add(new Groupes(4,"Groupe 4","SHABUNDA 1"));
        items.add(new Groupes(5,"Groupe 5","BEMI 1"));
        items.add(new Groupes(6,"Groupe 6","LUBERO 1"));
        items.add(new Groupes(7,"Groupe 7","RUTHURU 1"));
*/

        //setup materialviewpager

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
}
