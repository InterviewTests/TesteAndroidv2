package com.example.rossinyamaral.bank.loginScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rossinyamaral.bank.BankApplication;
import com.example.rossinyamaral.bank.R;
import com.example.rossinyamaral.bank.model.UserAccountModel;

import java.util.regex.Pattern;


interface LoginActivityInput {
    public void displayLoginData(UserAccountModel viewModel);

    public void displayLoginError(String message);
}


public class LoginActivity extends AppCompatActivity
        implements LoginActivityInput {

    public static String TAG = LoginActivity.class.getSimpleName();
    LoginInteractorInput output;
    LoginRouter router;

    EditText userEditText;
    EditText passwordEditText;
    Button loginButton;

    public UserAccountModel userAccountModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        BankApplication.getInstance().setStatusBarColor(this, android.R.color.transparent);

        userEditText = findViewById(R.id.userEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        LoginConfigurator.INSTANCE.configure(this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordEditText.getText().toString();
                if (checkPassword(password)) {
                    BankApplication.getInstance().loading(LoginActivity.this);
                    LoginRequest aLoginRequest = new LoginRequest();
                    //populate the request
                    aLoginRequest.user = userEditText.getText().toString();
                    aLoginRequest.password = password;
                    output.fetchLoginData(aLoginRequest);
                } else {
                    displayLoginError(getString(R.string.password_error_message));
                }
            }
        });
        String lastLogin = BankApplication.getInstance().getLastLogin();
        String lastPassword = BankApplication.getInstance().getLastPassword();
        if (!TextUtils.isEmpty(lastLogin)) {
            userEditText.setText(lastLogin);
            if (!TextUtils.isEmpty(lastPassword)) {
                passwordEditText.setText(lastPassword);
            }
        }
        // Do other work
    }

    public boolean checkPassword(String password) {
        return hasUppercaseLetter(password) &&
                hasAlphanumericCharacter(password) &&
                hasSpecialCharacter(password);
    }

    public boolean hasUppercaseLetter(String password) {
        return Pattern.compile("[A-Z]").matcher(password).find();
    }

    public boolean hasSpecialCharacter(String password) {
        return Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
    }

    public boolean hasAlphanumericCharacter(String password) {
        return Pattern.compile("[a-z0-9]").matcher(password).find();
    }


    @Override
    public void displayLoginData(UserAccountModel viewModel) {
        Log.e(TAG, "displayLoginData() called with: viewModel = [" + viewModel + "]");
        // Deal with the data
        router.onLoginClick(viewModel);
        BankApplication.getInstance().dismissLoading();
    }

    @Override
    public void displayLoginError(String message) {
        BankApplication.getInstance().dismissLoading();
        BankApplication.getInstance().alert(this, message, null);
    }
}
