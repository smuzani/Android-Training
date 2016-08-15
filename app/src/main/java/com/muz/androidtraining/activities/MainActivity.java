package com.muz.androidtraining.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        links.add(new MainMenuLink("Lesson 1.1-1.3: Action Bar & Menu", ToolbarMenuActivity.class));
        links.add(new MainMenuLink("Lesson 1.4: Snackbar", SnackbarActivity.class));
        links.add(new MainMenuLink("Lesson 1.5: Floating Action Button", FabActivity.class));
        links.add(new MainMenuLink("Lesson 2.1-2.2: Navigation Drawer & Back", NavDrawerActivity.class));
        links.add(new MainMenuLink("Lesson 2.3-2.5: Tabs, CardView, & Transition", TabbedActivity.class));
        links.add(new MainMenuLink("Lesson 2.6: Notification", NotificationActivity.class));
        links.add(new MainMenuLink("Lesson 3.2: Maps", MapsActivity.class));
        links.add(new MainMenuLink("Lesson 3.5: Firebase Signup", SignUpActivity.class));
    }
}
