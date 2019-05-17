package com.testeandroidv2;

import android.app.Application;

import com.orhanobut.hawk.Hawk;
import com.testeandroidv2.repository.response.Error;
import com.testeandroidv2.repository.response.UserAccount;

public class App extends Application {

    public static  UserAccount userAccount;
    public static Error error;

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
    }
}
