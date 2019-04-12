package com.android.bankapp;

import android.app.Application;
import android.content.ContextWrapper;

import com.pixplicity.easyprefs.library.Prefs;

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initEasyPrefs();
    }

    private void initEasyPrefs() {
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }

}
