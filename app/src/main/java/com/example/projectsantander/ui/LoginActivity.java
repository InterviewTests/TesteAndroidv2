package com.example.projectsantander.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectsantander.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{


    private EditText userName;
    private EditText password;
    private Button login;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void navigateToHome() {

    }

    @Override
    public void navigateToHomeAdmin() {

    }

    @Override
    public Context getContext() {
        return null;
    }
}
