package com.weza_lab.benenfance.optimumcoops.credit.adaptater;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.weza_lab.benenfance.optimumcoops.R;
import com.weza_lab.benenfance.optimumcoops.credit.Details_credit;
import com.weza_lab.benenfance.optimumcoops.database.DBHelper;
import com.weza_lab.benenfance.optimumcoops.database.DBQueries;
import com.weza_lab.benenfance.optimumcoops.parcel.CreditParcel;
import com.weza_lab.benenfance.optimumcoops.parcel.PersonnesParcel;
import com.weza_lab.benenfance.optimumcoops.pojo.Credit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CreditListRecyclerViewAdapter extends RecyclerView.Adapter<CreditListRecyclerViewAdapter.ViewHolder> {

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    final List<Credit> contents;
    final private Context mContext;
    final DBQueries dbQueries;
    final DBHelper dbHelper;
    final private View emptyView;

    public CreditListRecyclerViewAdapter(List<Credit> contents, Context mContext, View emptyView) {
        this.contents = contents;
        this.mContext = mContext;
        this.emptyView = emptyView;

        dbQueries = new DBQueries(mContext);
        dbHelper = new DBHelper(mContext);
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
                        .inflate(R.layout.credit_list_item_card_small, parent, false);

                return new ViewHolder(view) {
                };
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.credit_list_item_card_small, parent, false);
                return new ViewHolder(view) {
                };
            }
        }
        return null;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Credit c = contents.get(position);
        holder.mSomme.setText(c.getSomme_credit() + " CDF");
        holder.mTaux.setText("Taux = " + c.getTaux_credit() + " %");

        Date date_credit = new Date(c.getTimestamp());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        String date_string = df.format(date_credit);

        holder.mDate.setText(date_string);
        holder.mDate_ignor.setText(c.getTimestamp() + "");
        //holder.mDate_ignor.setText(c.getPhone_user_credit());

        // holder.mDate.setText((CharSequence) new Date(c.getTimestamp()));
        //holder.uImage.setImageResource(R.drawable.ic_monetization_on_black_24dp);
        holder.nbr.setText((position + 1) + "");
        if (c.getEtat_credit() == 1) {
            holder.stateImage.setImageResource(R.drawable.ic_lock_open_black_24dp);
            holder.mEtat.setText("Valide");
            holder.mEtat.setTextColor(R.color.green);
        } else {
            holder.stateImage.setImageResource(R.drawable.ic_lock_outline_black_24dp);
            holder.mEtat.setText("En attente");
            holder.mEtat.setTextColor(R.color.accent_color);
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
        public final ImageView stateImage;
        public final TextView mSomme, mEtat, mDate, mTaux, nbr, mDate_ignor;

        private int position = 0;
        private boolean isVisible;

        //Les donnnes

        public ViewHolder(View view) {
            super(view);
            mView = view;
            // uImage = view.findViewById(R.id.image_c);
            stateImage = view.findViewById(R.id.state_c);
            mSomme = view.findViewById(R.id.somme_c);
            mEtat = view.findViewById(R.id.etat_c);
            mDate = view.findViewById(R.id.datte_c);
            mTaux = view.findViewById(R.id.taux_c);
            nbr = view.findViewById(R.id.nbr);
            mDate_ignor = view.findViewById(R.id.mDatte_ignr);

            isVisible = false;

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            //Toast.makeText(mContext, "Click Item: " + position, Toast.LENGTH_SHORT).show();
            //Mettre les donnes dans le intent
            Intent i = new Intent(mContext, Details_credit.class);
            long o = Long.parseLong(mDate_ignor.getText() + "");

            dbQueries.open();
            CreditParcel c = dbQueries.readOneDatteCreditsParcel(o);
            PersonnesParcel p = dbQueries.readOnePersonnesParcel(c.getPhone_user_credit());

            dbQueries.close();
            Bundle bundle = new Bundle();
            bundle.putParcelable("credit", c);
            bundle.putParcelable("personne", p);

            i.putExtra("credit_data", bundle);

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