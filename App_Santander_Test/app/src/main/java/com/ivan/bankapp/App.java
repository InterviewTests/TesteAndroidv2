package com.ivan.bankapp;

import android.app.Application;

import java.lang.ref.WeakReference;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    private static WeakReference<Application> instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = new WeakReference<Application>(this);

        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("bankApp.realm")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfig);

    }

    public static Application getInstance() {
        return instance != null ? instance.get() : null;
    }

}