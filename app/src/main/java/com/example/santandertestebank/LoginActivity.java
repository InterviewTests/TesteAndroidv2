package com.example.santandertestebank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUser;
    EditText editTextPassword;
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

        loadUI ();
    }

    private void loadUI() {
        editTextUser = findViewById (R.id.edit_text_user);
        editTextPassword = findViewById (R.id.edit_text_password);
        buttonLogin = findViewById (R.id.button_login);
    }
}
