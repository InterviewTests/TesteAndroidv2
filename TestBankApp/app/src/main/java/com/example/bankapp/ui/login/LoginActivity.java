package com.example.bankapp.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bankapp.R;
import com.example.bankapp.helper.SaveUserPreferences;
import com.example.bankapp.model.user.UserAccount;
import com.example.bankapp.ui.dashboard.DashboardActivity;

public class LoginActivity extends AppCompatActivity implements LoginViewPresenter.loginView {
    private EditText editTextUser;
    private EditText editTextPassword;
    private Button buttonLogin;
    private LoginViewPresenter.loginPresenter presenter;
    private SaveUserPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loadUi();
        //preferences.clearPref();
        verifyUser();

        actionLoginButton();
    }

    private void actionLoginButton() {
        buttonLogin.setOnClickListener(v ->
                presenter.login(editTextUser.getText().toString(),
                editTextPassword.getText().toString())
        );

    }

    private void verifyUser() {
        String[] loginUser = preferences.getLoginUser();
        if (loginUser.length > 0) {
            editTextPassword.setText(loginUser[1]);
            editTextUser.setText(loginUser[0]);
        }
    }

    private void loadUi() {
        editTextUser = (EditText) findViewById(R.id.editTextUser);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        preferences = new SaveUserPreferences(this);
        presenter = new LoginPresenter(this);
    }

    @Override
    public void goToHome(UserAccount user) {
        Intent intentDash = new Intent(LoginActivity.this, DashboardActivity.class);
        intentDash.putExtra("userData", user);
        startActivity(intentDash);
    }

    @Override
    public void showErrorMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
