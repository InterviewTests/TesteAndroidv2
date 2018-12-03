package com.example.rossinyamaral.bank.loginScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.rossinyamaral.bank.R;


interface LoginActivityInput {
    public void displayLoginData(LoginViewModel viewModel);
}


public class LoginActivity extends AppCompatActivity
        implements LoginActivityInput {

    public static String TAG = LoginActivity.class.getSimpleName();
    LoginInteractorInput output;
    LoginRouter router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        LoginConfigurator.INSTANCE.configure(this);
        LoginRequest aLoginRequest = new LoginRequest();
        //populate the request


        output.fetchLoginData(aLoginRequest);
        // Do other work
    }


    @Override
    public void displayLoginData(LoginViewModel viewModel) {
        Log.e(TAG, "displayLoginData() called with: viewModel = [" + viewModel + "]");
        // Deal with the data
    }
}
