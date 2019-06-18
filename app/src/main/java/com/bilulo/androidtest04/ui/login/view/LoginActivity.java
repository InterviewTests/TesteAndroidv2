package com.bilulo.androidtest04.ui.login.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bilulo.androidtest04.R;
import com.bilulo.androidtest04.ui.login.configurator.LoginConfigurator;
import com.bilulo.androidtest04.ui.login.contract.LoginContract;
import com.bilulo.androidtest04.ui.login.router.LoginRouter;

public class LoginActivity extends AppCompatActivity implements LoginContract.ActivityContract {

    public LoginContract.InteractorContract interactor;
    public LoginRouter router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginConfigurator.configure(this);
    }

    @Override
    public void performLogin(String user, String password) {

    }
}
