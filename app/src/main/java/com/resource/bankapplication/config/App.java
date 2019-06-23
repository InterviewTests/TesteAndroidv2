package com.resource.bankapplication.config;

import android.app.Application;

public class App extends Application {

    private static App instance;
    private static RetrofitFactory retrofitFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        retrofitFactory = RetrofitFactory.getInstance();
    }

    public static App getInstance() {
        return instance;
    }

    public static RetrofitFactory getRetrofitFactory() {
        return retrofitFactory;
    }
}
