package com.example.testesantander;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Interactors.LoginInteractor;

public class MainActivity extends AppCompatActivity {
    private EditText etUser;
    private EditText etPassword;
    private LoginInteractor interactor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = (EditText) findViewById(R.id.etUser);
        etPassword = (EditText) findViewById(R.id.etPassword);
        Button btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interactor = new LoginInteractor(etUser, etPassword);
                if (interactor.validaUsuario())
                    interactor.logar(view.getContext());
                else {
                    Toast.makeText(view.getContext(), "Usuário e/ou Senha inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
