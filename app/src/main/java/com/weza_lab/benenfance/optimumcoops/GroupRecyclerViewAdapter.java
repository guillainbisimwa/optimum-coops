package com.weza_lab.benenfance.optimumcoops;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.weza_lab.benenfance.optimumcoops.pojo.Groupes;

import java.util.List;

public class GroupRecyclerViewAdapter extends RecyclerView.Adapter<GroupRecyclerViewAdapter.ViewHolder> {

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    final private Context mContext;
    List<Groupes> contents;

    public GroupRecyclerViewAdapter(List<Groupes> contents, Context mContext) {
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
                        .inflate(R.layout.list_item_card_big, parent, false);

                return new ViewHolder(view) {
                };
            }
            case TYPE_CELL: {

                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                return new ViewHolder(view) {
                };
            }
        }
        return null;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Groupes a = contents.get(position);
        holder.gUname.setText(a.getName_g());
        holder.gId.setText(a.getAdresse_g());
        holder.gImage.setImageResource(R.drawable.pic33);
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                break;
            case TYPE_CELL:
                break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public final View mView;
        public final ImageView gImage;
        public final TextView gUname, gId;

        private int position = 0;
        private boolean isVisible;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            gImage = view.findViewById(R.id.gImage);
            gUname = view.findViewById(R.id.gName);
            gId = view.findViewById(R.id.gId);

            isVisible = false;

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Toast.makeText(mContext, "Click Item: " + position, Toast.LENGTH_SHORT).show();
        }


        @Override
        public boolean onLongClick(View view) {
            position = getAdapterPosition();
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            Toast.makeText(mContext, "long Click item: " + gUname.getText(), Toast.LENGTH_SHORT).show();
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