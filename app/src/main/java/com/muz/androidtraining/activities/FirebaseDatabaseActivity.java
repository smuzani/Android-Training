package com.muz.androidtraining.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.muz.androidtraining.R;
import com.muz.androidtraining.adapters.DatabaseHolder;
import com.muz.androidtraining.models.Item;

public class FirebaseDatabaseActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_database);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Firebase Database");

        // Setup Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(FirebaseDatabaseActivity.this, AddItemActivity.class);
                startActivity(mainIntent);
            }
        });

        // Setup RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.messageRecyclerView);
        recyclerView.setHasFixedSize(true);

        // Setup Layout Manager
        LinearLayoutManager mManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mManager);

        // Point to the right files
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Snackbar snackbar = Snackbar.make(recyclerView, "Please log on first", Snackbar.LENGTH_SHORT);
            snackbar.show();
        } else {
            String mUserId = user.getUid();
            Query query = mDatabase.child("items").child(mUserId);
            final Snackbar snackbar = Snackbar.make(recyclerView, "Downloading data…", Snackbar.LENGTH_INDEFINITE);
            snackbar.show();
            FirebaseRecyclerAdapter<Item, DatabaseHolder> mAdapter = new FirebaseRecyclerAdapter<Item, DatabaseHolder>(Item.class, R.layout.row_layout, DatabaseHolder.class, query) {
                @Override
                protected void populateViewHolder(DatabaseHolder viewHolder, Item model, int position) {
                    snackbar.dismiss();
                    viewHolder.tvItem.setText(model.itemName);
                    viewHolder.tvDesc.setText(model.itemDescription);
                    viewHolder.tvPlace.setText(model.itemPlace);
                }
            };
            recyclerView.setAdapter(mAdapter);
        }
    }
}
