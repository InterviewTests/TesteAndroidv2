package com.luizroseiro.testeandroidv2.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luizroseiro.testeandroidv2.R;
import com.luizroseiro.testeandroidv2.services.DataService;
import com.luizroseiro.testeandroidv2.utils.Utils;
import com.luizroseiro.testeandroidv2.views.fragments.LoginFragment;
import com.luizroseiro.testeandroidv2.views.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    private static MainActivity mainActivity;
    public static MainActivity getMainActivity() {
        return mainActivity;
    }
    public static void setMainActivity(MainActivity mainActivity) {
        MainActivity.mainActivity = mainActivity;
    }

    public static DataService dataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMainActivity(this);
        dataService = new DataService(this);

        if (dataService.isLoggedIn())
            Utils.replaceFragment(R.id.container_main, new MainFragment());
        else
            Utils.replaceFragment(R.id.container_main, new LoginFragment());

    }

}
