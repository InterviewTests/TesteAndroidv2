package com.example.rossinyamaral.bank.loginScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.rossinyamaral.bank.BankApplication;
import com.example.rossinyamaral.bank.R;


interface LoginActivityInput {
    public void displayLoginData(LoginViewModel viewModel);
}


public class LoginActivity extends AppCompatActivity
        implements LoginActivityInput {

    public static String TAG = LoginActivity.class.getSimpleName();
    LoginInteractorInput output;
    LoginRouter router;

    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        loginButton = findViewById(R.id.loginButton);

        LoginConfigurator.INSTANCE.configure(this);
        LoginRequest aLoginRequest = new LoginRequest();
        //populate the request
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankApplication.getInstance().bankApi.login("test_user", "Test@1", null);
            }
        });
//        BankApplication.getInstance().bankApi.login("test_user", "Test@1", null);

        output.fetchLoginData(aLoginRequest);
        // Do other work
    }

    public boolean hasUppercaseLetter(String password) {
        return true;
    }

    public boolean hasSpecialCharacter(String password) {
        return true;
    }

    public boolean hasAlphanumericCharacter(String password) {
        return true;
    }


    @Override
    public void displayLoginData(LoginViewModel viewModel) {
        Log.e(TAG, "displayLoginData() called with: viewModel = [" + viewModel + "]");
        // Deal with the data
    }
}
