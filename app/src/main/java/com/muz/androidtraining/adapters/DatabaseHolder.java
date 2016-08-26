package com.muz.androidtraining.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.muz.androidtraining.R;

/**
 * Created by Muz on 2016-08-14.
 */
public class DatabaseHolder extends RecyclerView.ViewHolder {
    public TextView tvItem;
    public TextView tvPlace;
    public TextView tvDesc;

    public DatabaseHolder(View itemView) {
        super(itemView);
        tvItem = (TextView) itemView.findViewById(R.id.itemTextView);
        tvPlace = (TextView) itemView.findViewById(R.id.placeTextView);
        tvDesc = (TextView) itemView.findViewById(R.id.descTextView);
    }
}
