package com.resource.bankapplication.config;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class App extends Application {

    private static App instance;
    private static RetrofitFactory retrofitFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        retrofitFactory = RetrofitFactory.getInstance();
    }

    public static boolean isConected(Context context){
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info!=null && info.isConnected();
    }

    public static App getInstance() {
        return instance;
    }

    public static RetrofitFactory getRetrofitFactory() {
        return retrofitFactory;
    }
}
