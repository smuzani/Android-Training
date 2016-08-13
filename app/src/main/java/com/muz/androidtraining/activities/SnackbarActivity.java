package com.muz.androidtraining.activities;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.muz.androidtraining.R;

public class SnackbarActivity extends AppCompatActivity {
    LinearLayout llMain;
    Button btSimple, btAction, btCustom;
    final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);

        llMain = (LinearLayout) findViewById(R.id.ll_main);
        btSimple = (Button) findViewById(R.id.bt_simple);
        btAction = (Button) findViewById(R.id.bt_action);
        btCustom = (Button) findViewById(R.id.bt_custom);

        btSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(llMain, "This is a simple snackbar", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

        btAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar2 = Snackbar.make(llMain, "Message is deleted", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Snackbar snackbar2 = Snackbar.make(llMain, "Message is restored!", Snackbar.LENGTH_SHORT);
                                snackbar2.show();
                            }
                        });
                snackbar2.show();
            }
        });

        btCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar3 = Snackbar.make(llMain, "No Internet connection!", Snackbar.LENGTH_LONG)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });
                snackbar3.setActionTextColor(Color.RED);
                View sbView = snackbar3.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                snackbar3.show();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
