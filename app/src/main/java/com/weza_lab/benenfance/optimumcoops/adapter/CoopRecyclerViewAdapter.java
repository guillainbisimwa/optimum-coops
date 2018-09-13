package com.weza_lab.benenfance.optimumcoops.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weza_lab.benenfance.optimumcoops.R;
import com.weza_lab.benenfance.optimumcoops.pojo.Cooperatives;

import java.util.List;

public class CoopRecyclerViewAdapter extends RecyclerView.Adapter<CoopRecyclerViewAdapter.ViewHolder> {

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    final List<Cooperatives> contents;

    public CoopRecyclerViewAdapter(List<Cooperatives> contents) {
        this.contents = contents;
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
                /*view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);*/
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

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Cooperatives a = contents.get(position);
        holder.mUname.setText(a.getName_c());
        holder.mPhone.setText(a.getPhone_c());
        holder.uImage.setImageResource(R.drawable.logo4);
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                break;
            case TYPE_CELL:
                break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public final View mView;
        public final ImageView uImage;
        public final TextView mUname, mPhone;


        private int position = 0;
        private boolean isVisible;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            uImage = view.findViewById(R.id.image_a);

            mUname = view.findViewById(R.id.nom_a);
            mPhone = view.findViewById(R.id.phone_a);

            isVisible = false;

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            //Toast.makeText(this,"Click item "+position,Toast.LENGTH_SHORT).show();
        }


        @Override
        public boolean onLongClick(View view) {
            position = getAdapterPosition();


            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);

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