package com.gft.testegft.base;

import android.content.SharedPreferences;

import com.gft.testegft.di.component.AppComponent;
import com.gft.testegft.di.component.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


public class GftApplication extends DaggerApplication {

    public static SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = getSharedPreferences( getPackageName() + "_preferences", MODE_PRIVATE);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent component = DaggerAppComponent.builder().application(this).build();
        component.inject(this);

        return component;
    }

}