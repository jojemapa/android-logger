# Android Logging - Timber Implementation

This is my implementation for logging with files using Timber.

#### [View Timber Library](https://github.com/JakeWharton/timber)

[![Build Status](https://travis-ci.org/afollestad/material-dialogs.svg)](https://travis-ci.org/afollestad/material-dialogs)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/0a4acc30a9ce440087f7688735359bb8)](https://www.codacy.com/app/drummeraidan_50/material-dialogs?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=afollestad/material-dialogs&amp;utm_campaign=Badge_Grade)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

---

![Screenshots](https://raw.githubusercontent.com/pjdepractica/android-logger/master/art/showcase-picture.png)

## FileLoggingTree extends Timber.DebugTree

#### [FileLoggingTree](documentation/CORE.md)

The `FileLoggingTree extends Timber.DebugTree` is the more important, It contains everything you need. It contains all
code for make file i personalize your logging files.


## Implementation

1. First add the Timber Library to `build.gradle`.

```gradle
dependencies {
  ...
  implementation 'com.jakewharton.timber:timber:4.7.1'
}
```

2. Second, make `Application` class for your app and register it on `Manifest`.

```java
public class AndroidLogger extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG)
            Timber.plant(new DebugLoggingTree());//Ignore this line for debug
        else
            Timber.plant(new FileLoggingTree());//Production debug with file

    }
}
```

and register and your manifest

```xml
<application
        android:name=".AndroidLogger"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        .....
        .....
```

3. Use it

```java
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
```

This methods are statically then you can call it from everywhere.

## Output

### Light theme
![Screenshots](https://raw.githubusercontent.com/pjdepractica/android-logger/master/art/output.png)

### Dark Theme
![Screenshots](https://raw.githubusercontent.com/pjdepractica/android-logger/master/art/output-dark.png)

