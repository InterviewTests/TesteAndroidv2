package com.example.testesantander;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Interactors.LoginInteractor;
import Presenters.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginActivityInput{
    private EditText etUser;
    private EditText etPassword;
    private LoginInteractor interactor;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser = (EditText) findViewById(R.id.etUser);
        etPassword = (EditText) findViewById(R.id.etPassword);
        Button btLogin = (Button) findViewById(R.id.btLogin);
        presenter = new LoginPresenter(this);
        interactor = new LoginInteractor(presenter);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interactor.criaUsuario(etUser, etPassword);
                if (interactor.validaUsuario()) {
                    interactor.logar();
                } else {
                    Toast.makeText(view.getContext(), "Usuário e/ou Senha inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void injetarDependencia(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
