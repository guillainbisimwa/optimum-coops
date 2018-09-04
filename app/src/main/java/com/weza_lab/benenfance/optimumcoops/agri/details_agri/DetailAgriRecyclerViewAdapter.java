package com.weza_lab.benenfance.optimumcoops.agri.details_agri;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weza_lab.benenfance.optimumcoops.R;
import com.weza_lab.benenfance.optimumcoops.pojo.Personnes;

import java.util.List;

public class DetailAgriRecyclerViewAdapter extends RecyclerView.Adapter<DetailAgriRecyclerViewAdapter.ViewHolder> {

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    final List<Personnes> contents;
    final private Context mContext;

    public DetailAgriRecyclerViewAdapter(List<Personnes> contents, Context mContext) {
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
                        .inflate(R.layout.detail_dgri_card_big, parent, false);

                return new ViewHolder(view) {
                };
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.detail_dgri_card_big, parent, false);
                return new ViewHolder(view) {
                };
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Personnes a = contents.get(position);

        holder._icon_pic_detais.setImageResource(R.drawable.ic_person_black_24dp);

        holder._nom_f_details.setText(a.getNom_a());
        holder._prenom_details.setText(a.getPostnom_a());
        holder._sexe_details.setText(a.getGender_a());
        holder._phone_details.setText(a.getPhone_a());
        holder._adress_details.setText(a.getAdresse_a());
        if (a.getIs_validate_a() == 1)
            holder._pic_etat_details.setImageResource(R.drawable.ic_lock_open_black_24dp);
        else holder._pic_etat_details.setImageResource(R.drawable.ic_lock_outline_black_24dp);

        if (a.getDefault_type() == 100)
            holder._titre_details.setText(R.string.inf0_agri);
        else if (a.getDefault_type() == 101)
            holder._titre_details.setText(R.string.inf0_petit_com);
        else if (a.getDefault_type() == 102)
            holder._titre_details.setText(R.string.inf0_em);
        else if (a.getDefault_type() == 103)
            holder._titre_details.setText(R.string.inf0_entre);

        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                break;
            case TYPE_CELL:
                break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public final View mView;
        public final ImageView _icon_pic_detais, _pic_etat_details;
        public final TextView _titre_details;
        public final TextView _nom_f_details;
        public final TextView _prenom_details;
        public final TextView _sexe_details;
        public final TextView _phone_details;
        public final TextView _adress_details;

        private int position = 0;
        private boolean isVisible;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            _icon_pic_detais = view.findViewById(R.id._icon_pic_detais);
            _titre_details = view.findViewById(R.id._titre_details);
            _nom_f_details = view.findViewById(R.id._nom_f_details);
            _prenom_details = view.findViewById(R.id._prenom_details);
            _sexe_details = view.findViewById(R.id._sexe_details);
            _phone_details = view.findViewById(R.id._phone_details);
            _adress_details = view.findViewById(R.id._adress_details);
            _pic_etat_details = view.findViewById(R.id._pic_etat_details);

            isVisible = false;
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            //Toast.makeText(mContext,"Click Item: "+position,Toast.LENGTH_SHORT).show
            //mContext.startActivity(new Intent(mContext,Details_agri.class ));
        }

        @Override
        public boolean onLongClick(View view) {
            position = getAdapterPosition();
            //view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            //Toast.makeText(mContext,"long Click item: "+mUname.getText(),Toast.LENGTH_SHORT).show();
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