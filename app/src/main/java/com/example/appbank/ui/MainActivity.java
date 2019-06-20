package com.example.appbank.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbank.R;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView textUser;
    private TextView textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadUi();

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (login(textUser.getText().toString(), textPassword.getText().toString())) {
                    Intent intent = new Intent(MainActivity.this, Account.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Usuário ou senha inválidos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean login(String User, String Password) {
        if (User.equals("admin") && Password.equals("123")) {
            return true;
        } else {
            return false;
        }

    }

    private void loadUi() {
        textPassword = findViewById(R.id.textPassword);
        textUser = findViewById(R.id.textUser);
    }
}
