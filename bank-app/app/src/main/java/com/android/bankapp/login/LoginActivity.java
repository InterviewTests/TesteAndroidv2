package com.android.bankapp.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.bankapp.R;

public class LoginActivity extends AppCompatActivity  implements LoginActivityInput {

    LoginInteractorInput output;
    LoginRouterInput router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginError() {

    }
}
