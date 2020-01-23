package com.msbm.bank.loginScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.msbm.bank.R;
import com.msbm.bank.entities.User;

public class LoginActivity extends AppCompatActivity implements LoginInteractorInput {

    public static String TAG = LoginActivity.class.getSimpleName();

    protected LoginInteractor loginInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        LoginConfigurator.INSTANCE.configure(this);
    }

    @Override
    public void doLogin(LoginRequest loginRequest) {
        User user = new User();
        user.setUsername("");
        user.setPassword("");

        LoginRequest request = new LoginRequest();
        request.user = user;

        this.loginInteractor.doLogin(request);
    }
}
