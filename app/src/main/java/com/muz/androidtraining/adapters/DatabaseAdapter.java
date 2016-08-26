package com.muz.androidtraining.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muz.androidtraining.R;
import com.muz.androidtraining.models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muz on 2016-08-14.
 */
public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseHolder> {
    List<Item> items = new ArrayList<>();
    private Context context;

    public DatabaseAdapter(Context context, List<Item> items) {
        Log.v("ContentAdapter", "Constructor");
        this.context = context;
        this.items = items;
    }

    @Override
    public DatabaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v("ContentAdapter", "OnCreateViewHolder");
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.row_layout, parent, false);

        // Return a new holder instance
        DatabaseHolder viewHolder = new DatabaseHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DatabaseHolder holder, int position) {
        Log.v("ContentAdapter", "OnBindViewHolder");
        Item item = items.get(position);
        holder.tvPlace.setText(item.itemPlace);
        holder.tvItem.setText(item.itemName);
        holder.tvDesc.setText(item.itemDescription);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
