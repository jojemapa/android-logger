package com.example.androidlogger;

import android.app.Application;

import timber.log.Timber;

public class AndroidLogger extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG)
            Timber.plant(new FileLoggingTree());//DEbugTree
        else
            Timber.plant(new FileLoggingTree());

    }
}
