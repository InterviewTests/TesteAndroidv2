package com.gft.testegft.base;

import android.app.Application;

public class GftApplication extends Application {

    protected AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = initDagger();
        appComponent.inject(this);

    }

    protected AppComponent initDagger() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}