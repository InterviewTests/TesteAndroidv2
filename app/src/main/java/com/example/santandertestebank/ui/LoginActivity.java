package com.example.santandertestebank.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.santandertestebank.R;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUser;
    EditText editTextPassword;
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        getSupportActionBar ().hide ();

        loadUI ();

        buttonLogin.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                openPaymentList ();
            }
        });
    }

    private void openPaymentList() {
        Intent intent = new Intent (this, BankPaymentsActivity.class);
        startActivity (intent);
    }

    private void loadUI() {
        editTextUser = findViewById (R.id.edit_text_user);
        editTextPassword = findViewById (R.id.edit_text_password);
        buttonLogin = findViewById (R.id.button_login);
    }
}
