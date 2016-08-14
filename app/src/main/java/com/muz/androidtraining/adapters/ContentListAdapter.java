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

/**
 * Created by Muz on 2016-08-14.
 */
public class ContentListAdapter extends RecyclerView.Adapter<CardViewHolder> {
    // Set numbers of List in RecyclerView
    private static final int LENGTH = 18;
    private final String[] mPlaces;
    private final String[] mPlaceDesc;
    private final Drawable[] mPlacePictures;
    private Context context;

    public ContentListAdapter(Context context) {
        this.context = context;
        Resources resources = context.getResources();
        mPlaces = resources.getStringArray(R.array.places);
        mPlaceDesc = resources.getStringArray(R.array.place_desc);
        TypedArray a = resources.obtainTypedArray(R.array.places_picture);
        mPlacePictures = new Drawable[a.length()];
        for (int i = 0; i < mPlacePictures.length; i++) {
            mPlacePictures[i] = a.getDrawable(i);
        }
        a.recycle();
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.list_places, parent, false);

        // Return a new holder instance
        CardViewHolder viewHolder = new CardViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Log.v("ContentAdapter", "OnBindViewHolder");
        holder.picture.setImageDrawable(mPlacePictures[position % mPlacePictures.length]);
        holder.name.setText(mPlaces[position % mPlaces.length]);
        holder.description.setText(mPlaceDesc[position % mPlaceDesc.length]);
        holder.stringDesc = mPlaceDesc[position % mPlaceDesc.length];
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return LENGTH;
    }
}
