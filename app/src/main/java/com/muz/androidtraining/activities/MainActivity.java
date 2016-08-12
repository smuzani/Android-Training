package com.muz.androidtraining.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.muz.androidtraining.adapters.MainPageListAdapter;

import com.muz.androidtraining.R;
import com.muz.androidtraining.models.MainMenuLink;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Activity activity = this;
    List<MainMenuLink> links = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        ListView lv = (ListView) findViewById(R.id.lv);
        initListView();
        lv.setAdapter(new MainPageListAdapter(activity, links));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                MainMenuLink link = links.get(position);
                Intent intent = new Intent(activity, link.getActivityClass());
                startActivity(intent);
            }
        });
    }

    private void initListView(){
        links.add(new MainMenuLink("Lesson 1-3: Action Bar, Menu", ToolbarMenuActivity.class));
        links.add(new MainMenuLink("Lesson 4: Snackbar", SnackbarActivity.class));
    }
}
