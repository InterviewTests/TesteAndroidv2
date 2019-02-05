package com.luizroseiro.testeandroidv2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luizroseiro.testeandroidv2.mainScreen.MainFragment;
import com.luizroseiro.testeandroidv2.loginScreen.LoginFragment;
import com.luizroseiro.testeandroidv2.utils.AppPreferences;
import com.luizroseiro.testeandroidv2.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private static MainActivity mainActivity;
    public static Context getContext() {
        return mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = MainActivity.this;

    }

    @Override
    protected void onStart() {

        super.onStart();

        if (AppPreferences.isUserLoggedIn())
            Utils.replaceFragment(new MainFragment());
        else
            Utils.replaceFragment(new LoginFragment());

    }
}
