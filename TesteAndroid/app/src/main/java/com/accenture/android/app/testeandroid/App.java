package com.accenture.android.app.testeandroid;

import android.app.Application;
import android.util.Log;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public class App extends Application {
    private final static String TAG = "CustomLog - " + App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "Application started.");
    }
}
