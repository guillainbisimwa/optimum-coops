package com.weza_lab.benenfance.optimumcoops.group.Details_group;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weza_lab.benenfance.optimumcoops.R;
import com.weza_lab.benenfance.optimumcoops.pojo.Groups;

import java.util.List;

public class DetailGroupRecyclerViewAdapter extends RecyclerView.Adapter<DetailGroupRecyclerViewAdapter.ViewHolder> {

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    final List<Groups> contents;
    final private Context mContext;

    public DetailGroupRecyclerViewAdapter(List<Groups> contents, Context mContext) {
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
                        .inflate(R.layout.detail_group_card_big, parent, false);

                return new ViewHolder(view) {
                };
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_small, parent, false);
                return new ViewHolder(view) {
                };
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Groups a = contents.get(position);

        holder._icon_pic_detais.setImageResource(R.drawable.ic_person_black_24dp);

        holder._nom_f_details.setText(a.getName_group());
        holder._adress_details.setText(a.getAdress_group());
        holder._titre_details.setText(R.string.inf0_group);
        holder._nbr_user_details.setText(a.getNbr_users() + "");
        if (a.getType_group() == 100)
            holder._nbr_user.setText(R.string.agri);
        else if (a.getType_group() == 101)
            holder._nbr_user.setText(R.string.petit_com);
        else if (a.getType_group() == 102)
            holder._nbr_user.setText(R.string.em);
        else if (a.getType_group() == 103)
            holder._nbr_user.setText(R.string.entre);

        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                break;
            case TYPE_CELL: {

                break;
            }
        }
    }
    //RecyclerView.ViewHolder;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public final View mView;
        public final ImageView _icon_pic_detais;
        public final TextView _titre_details;
        public final TextView _nom_f_details;
        public final TextView _adress_details, _nbr_user, _nbr_user_details;

        private int position = 0;
        private boolean isVisible;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            _icon_pic_detais = view.findViewById(R.id._icon_pic_detais);
            _titre_details = view.findViewById(R.id._titre_details);
            _nom_f_details = view.findViewById(R.id._nom_f_details);
            _nbr_user = view.findViewById(R.id._nbr_user);
            _nbr_user_details = view.findViewById(R.id._nbr_user_details);
            _adress_details = view.findViewById(R.id._adresss_details);

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