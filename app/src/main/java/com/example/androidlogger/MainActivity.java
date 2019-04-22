package com.example.androidlogger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timber.v("onCreate verbose logs here");
        Timber.d("onCreate debug logs here");
        Timber.i("onCreate info logs here");
        Timber.w("onCreate warning logs here");
        Timber.e("onCreate[ error logs here");

        Button loggerBtn = findViewById(R.id.loggerBtn);
        Button ostiasBtn = findViewById(R.id.ostiasBtn);

        loggerBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Timber.v("loggerBtn verbose logs here");
                Timber.d("loggerBtn debug logs here");
                Timber.i("loggerBtn info logs here");
                Timber.w("loggerBtn warning logs here");
                Timber.e("loggerBtn error logs here");
            }
        });

        ostiasBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Timber.v("ostiasBtn verbose logs here");
                Timber.d("ostiasBtn debug logs here");
                Timber.i("ostiasBtn info logs here");
                Timber.w("ostiasBtn warning logs here");
                Timber.e("ostiasBtn error logs here");
            }
        });

    }
}
