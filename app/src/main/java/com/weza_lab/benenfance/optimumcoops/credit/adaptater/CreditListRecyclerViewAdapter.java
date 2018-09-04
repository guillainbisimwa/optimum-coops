package com.weza_lab.benenfance.optimumcoops.credit.adaptater;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.weza_lab.benenfance.optimumcoops.R;
import com.weza_lab.benenfance.optimumcoops.pojo.Credit;

import java.util.List;

public class CreditListRecyclerViewAdapter extends RecyclerView.Adapter<CreditListRecyclerViewAdapter.ViewHolder> {

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    final List<Credit> contents;
    final private Context mContext;

    /*//bd
    static final DBQueries dbQueries;
    static final DBHelper dbHelper;
*/
    public CreditListRecyclerViewAdapter(List<Credit> contents, Context mContext) {
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
        holder.mDate.setText(c.getTimestamp() + "");
        // holder.mDate.setText((CharSequence) new Date(c.getTimestamp()));
        holder.uImage.setImageResource(R.drawable.ic_monetization_on_black_24dp);
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
        public final ImageView uImage, stateImage;
        public final TextView mSomme, mEtat, mDate, mTaux;

        private int position = 0;
        private boolean isVisible;

        //Les donnnes

        public ViewHolder(View view) {
            super(view);
            mView = view;
            uImage = view.findViewById(R.id.image_c);
            stateImage = view.findViewById(R.id.state_c);
            mSomme = view.findViewById(R.id.somme_c);
            mEtat = view.findViewById(R.id.etat_c);
            mDate = view.findViewById(R.id.datte_c);
            mTaux = view.findViewById(R.id.taux_c);

            isVisible = false;

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Toast.makeText(mContext, "Click Item: " + position, Toast.LENGTH_SHORT).show();
            //Mettre les donnes dans le intent
            /*Intent i = new Intent(mContext, Details_agri.class);
            i.putExtra("mPhone", mPhone.getText());
            i.putExtra("mName", mUname.getText());
            i.putExtra("mType", mType.getText());
            mContext.startActivity(i);*/
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