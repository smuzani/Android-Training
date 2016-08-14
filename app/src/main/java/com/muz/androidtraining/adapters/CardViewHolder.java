package com.muz.androidtraining.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.muz.androidtraining.R;
import com.muz.androidtraining.activities.DetailActivity;

/**
 * Created by Muz on 2016-08-14.
 */
public class CardViewHolder extends RecyclerView.ViewHolder {
    public ImageView picture;
    public TextView name;
    public TextView description;
    public int position;
    public String stringDesc;

    public CardViewHolder(View itemView) {
        super(itemView);
        picture = (ImageView) itemView.findViewById(R.id.card_image);
        name = (TextView) itemView.findViewById(R.id.card_title);
        description = (TextView) itemView.findViewById(R.id.card_text);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_POSITION, position);
                intent.putExtra(DetailActivity.EXTRA_DESC, stringDesc);
                context.startActivity(intent);
            }
        });
    }
}
