package com.weza_lab.benenfance.optimumcoops.agri;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weza_lab.benenfance.optimumcoops.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsAgriculteurFragment extends Fragment {

    public DetailsAgriculteurFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_add_agriculteurs, container, false);
        return inflater.inflate(R.layout.agriculteur_details, container, false);
    }
}
