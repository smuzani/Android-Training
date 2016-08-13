package com.muz.androidtraining.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.muz.androidtraining.R;

/**
 * Created by Muz on 2016-08-14.
 */
public class CustomViewHolder extends RecyclerView.ViewHolder {
    CardView cv;
    TextView title;
    TextView description;
    ImageView imageView;

    public CustomViewHolder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        imageView = (ImageView) imageView.findViewById(R.id.imageView);
    }
}
