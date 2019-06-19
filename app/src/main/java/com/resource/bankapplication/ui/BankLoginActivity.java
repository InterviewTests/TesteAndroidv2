package com.resource.bankapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.resource.bankapplication.R;

import java.util.List;

public class BankLoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_login);

        loadUi();
        loadActions();
    }

    private void loadActions() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BankLoginActivity.this, BankEntryActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadUi() {
        editTextUsername = findViewById(R.id.edit_text_username);
        editTextUsername = findViewById(R.id.edit_text_password);
        buttonLogin = findViewById(R.id.button_login);
    }
}
