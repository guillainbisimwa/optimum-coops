package com.weza_lab.benenfance.optimumcoops.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.weza_lab.benenfance.optimumcoops.R;
import com.weza_lab.benenfance.optimumcoops.agri.details_agri.Details_agri;
import com.weza_lab.benenfance.optimumcoops.pojo.Personnes;

import java.util.List;

public class PersRecyclerViewAdapter extends RecyclerView.Adapter<PersRecyclerViewAdapter.ViewHolder> {

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    final List<Personnes> contents;
    final private Context mContext;

    public PersRecyclerViewAdapter(List<Personnes> contents, Context mContext) {
        this.contents = contents;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_HEADER: {

                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.agri_list_item_card_small, parent, false);

                return new ViewHolder(view) {
                };
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.agri_list_item_card_small, parent, false);
                return new ViewHolder(view) {
                };
            }
        }
        return null;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Personnes a = contents.get(position);
        holder.mUname.setText(a.getNom_a() + " " + a.getPostnom_a());
        holder.mPhone.setText(a.getPhone_a());
        holder.uImage.setImageResource(R.drawable.ic_person_black_24dp);

        if (a.getDefault_type() == 100)
            holder.mType.setText("Agriculteurs");
        else if (a.getDefault_type() == 101)
            holder.mType.setText("Commercant");
        else if (a.getDefault_type() == 102)
            holder.mType.setText("Employer");
        else if (a.getDefault_type() == 103)
            holder.mType.setText("Entrepreneur");

        if (a.getIs_validate_a() == 1) {
            holder.stateImage.setImageResource(R.drawable.ic_lock_open_black_24dp);
            holder.mType.setTextColor(R.color.green);
        } else {
            holder.stateImage.setImageResource(R.drawable.ic_lock_outline_black_24dp);
            holder.mType.setTextColor(R.color.red);
        }


        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                break;
            case TYPE_CELL:
                break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public final View mView;
        public final ImageView uImage, stateImage;
        public final TextView mUname, mPhone, mType;

        private int position = 0;
        private boolean isVisible;

        //Les donnnes

        public ViewHolder(View view) {
            super(view);
            mView = view;
            uImage = view.findViewById(R.id.image_a);
            stateImage = view.findViewById(R.id.state_a);
            mUname = view.findViewById(R.id.nom_a);
            mPhone = view.findViewById(R.id.phone_a);
            mType = view.findViewById(R.id.type_a);

            isVisible = false;

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            //Toast.makeText(mContext,"Click Item: "+position,Toast.LENGTH_SHORT).show();
            //Mettre les donnes dans le intent
            Intent i = new Intent(mContext, Details_agri.class);
            i.putExtra("mPhone", mPhone.getText());
            i.putExtra("mName", mUname.getText());
            i.putExtra("mType", mType.getText());
            mContext.startActivity(i);
        }


        @Override
        public boolean onLongClick(View view) {
            position = getAdapterPosition();
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            Toast.makeText(mContext, "Delection comming soon ", Toast.LENGTH_SHORT).show();


            return true;
        }

        public boolean isVisible() {
            return isVisible;
        }

        public void setVisible(boolean visible) {
            isVisible = visible;
        }
    }
}