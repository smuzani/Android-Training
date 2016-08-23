package com.muz.androidtraining.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.muz.androidtraining.R;
import com.muz.androidtraining.models.Item;

import java.util.HashMap;
import java.util.Map;

public class AddItemActivity extends AppCompatActivity {

    EditText itemNameText, itemDescriptionText, itemPlaceText;
    Button sendBtn;
    private DatabaseReference mDatabase;
    String mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        itemNameText = (EditText) findViewById(R.id.editText);
        itemDescriptionText = (EditText) findViewById(R.id.editText2);
        itemPlaceText = (EditText) findViewById(R.id.editText3);
        sendBtn = (Button) findViewById(R.id.button2);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = mDatabase.child("items").push().getKey();
                Item newItem = new Item(itemNameText.getText().toString(), itemDescriptionText.getText().toString(), itemPlaceText.getText().toString());
                Map<String, Object> itemValues = newItem.toMap();
                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put("/items/" + mUserId + "/" + key, itemValues);
                mDatabase.updateChildren(childUpdates)
                        .addOnCompleteListener(AddItemActivity.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(AddItemActivity.this, "Succesfully Added", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(AddItemActivity.this, "Failed to add: " + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
}
