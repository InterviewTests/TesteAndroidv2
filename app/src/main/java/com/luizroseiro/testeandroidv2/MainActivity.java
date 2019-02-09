package com.luizroseiro.testeandroidv2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luizroseiro.testeandroidv2.mainScreen.MainFragment;
import com.luizroseiro.testeandroidv2.loginScreen.LoginFragment;
import com.luizroseiro.testeandroidv2.models.UserModel;
import com.luizroseiro.testeandroidv2.utils.AppPreferences;
import com.luizroseiro.testeandroidv2.utils.Utils;

import static com.luizroseiro.testeandroidv2.utils.Statics.BUNDLE_USER_MODEL;

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

        if (AppPreferences.isUserLoggedIn()) {
            UserModel userModel = new UserModel();
            userModel.setUserId(AppPreferences.getUserId());
            userModel.setName(AppPreferences.getUserName());
            userModel.setAgency(AppPreferences.getUserAgency());
            userModel.setBankAccount(AppPreferences.getUserAccount());
            userModel.setBalance(AppPreferences.getUserBalance());

            Bundle bundle = new Bundle();
            bundle.putSerializable(BUNDLE_USER_MODEL, userModel);

            Utils.replaceFragment(new MainFragment(), bundle);
        }
        else
            Utils.replaceFragment(new LoginFragment(), null);

    }

}