package com.muz.androidtraining.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.muz.androidtraining.R;
import com.muz.androidtraining.adapters.TabPagerAdapter;
import com.muz.androidtraining.fragments.CardContentFragment;
import com.muz.androidtraining.fragments.ListContentFragment;
import com.muz.androidtraining.fragments.TileContentFragment;

public class TabbedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tabs");

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Set tabs inside toolbar
        tabs.setupWithViewPager(viewPager);
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ListContentFragment(), "ListView");
        adapter.addFragment(new TileContentFragment(), "TileView");
        adapter.addFragment(new CardContentFragment(), "CardView");
        viewPager.setAdapter(adapter);
    }
}
