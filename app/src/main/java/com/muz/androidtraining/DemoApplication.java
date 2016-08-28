package com.muz.androidtraining;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * This is an extension of Application, required to support Facebook Login
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the SDK before executing any other operations,
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}