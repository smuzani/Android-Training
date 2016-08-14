package com.muz.androidtraining.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.muz.androidtraining.R;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("Notifications");
    }

    public void createNotification(View view) {
        Intent showIntent = new Intent(this, SnackbarActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, showIntent, 0);

        Notification noti = new NotificationCompat.Builder(this)
                .setContentTitle("Don't forget to wake up!")
                .setContentText("Time to wake up")
                .setSmallIcon(R.drawable.alarm_clock)
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, noti);
    }
}
