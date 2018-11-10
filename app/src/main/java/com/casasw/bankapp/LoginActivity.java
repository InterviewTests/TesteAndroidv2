package com.casasw.bankapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


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
        //do the setup

        LoginConfigurator.INSTANCE.configure(this);

        //populate the request


        fetchLoginData();
        // Do other work
    }

    public void fetchLoginData(){
        LoginRequest loginRequest = new LoginRequest();
        output.fetchLoginData(loginRequest);
    }

    @Override
    public void displayLoginData(LoginViewModel viewModel) {
        Log.e(TAG, "displayLoginData() called with: viewModel = [" + viewModel + "]");
        // Deal with the data
    }
}
