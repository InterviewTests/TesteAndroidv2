package com.everis.TesteAndroidv2.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.everis.TesteAndroidv2.R;
import com.everis.TesteAndroidv2.Validadores;

public class MainActivity extends AppCompatActivity {
    EditText etUser, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVars();
    }

    private void initVars() {
        etUser = findViewById(R.id.et_user);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUser.getText().toString();
                String password = etPassword.getText().toString();
                if (Validadores.isValidCPF(user) || Validadores.isEmail(user)) {
                    if (Validadores.isValidPassword(password)) {

                        //TODO: Inserir validador via API

                        Intent intent = new Intent(MainActivity.this, ExtratoActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Senha não confere com os padrões", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "CPF ou e-mail inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}