package com.example.rossinyamaral.bank.loginScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    EditText userEditText;
    EditText passwordEditText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        userEditText = findViewById(R.id.userEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        LoginConfigurator.INSTANCE.configure(this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginRequest aLoginRequest = new LoginRequest();
                //populate the request
                aLoginRequest.user = userEditText.getText().toString();
                aLoginRequest.password = passwordEditText.getText().toString();
                output.fetchLoginData(aLoginRequest);
            }
        });
//        BankApplication.getInstance().bankApi.login("test_user", "Test@1", null);
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
