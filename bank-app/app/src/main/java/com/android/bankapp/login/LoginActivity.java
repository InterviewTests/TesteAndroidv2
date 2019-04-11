package com.android.bankapp.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.bankapp.R;

public class LoginActivity extends AppCompatActivity implements LoginActivityInput {

    LoginInteractorInput output;
    LoginRouterInput router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginConfigurator.INSTANCE.configure(this);
        output.doLogin("test_user", "Test@1");
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginError() {

    }
}
