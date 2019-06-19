package com.example.bankapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bankapp.CurrencyActivity;
import com.example.bankapp.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.loginView{
    private EditText editTextUser;
    private EditText editTextPassword;
    private Button buttonLogin;
    private LoginContract.loginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUser = (EditText)findViewById(R.id.editTextUser);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        presenter = new LoginPresenter(this);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, CurrencyActivity.class);
//                startActivity(intent);
                presenter.login(editTextUser.getText().toString(),editTextPassword.getText().toString());
            }
        });
    }

    @Override
    public void goToHome() {

    }

    @Override
    public void showErrorMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
