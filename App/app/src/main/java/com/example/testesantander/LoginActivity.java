package com.example.testesantander;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Domain.UserAccount;
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

        etUser = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPassword);
        Button btLogin = findViewById(R.id.btLogin);
        presenter = new LoginPresenter(this);
        interactor = new LoginInteractor(presenter);

        SharedPreferences prefs = getSharedPreferences("login_data", MODE_PRIVATE);
        if (!prefs.getString("usuario", "").isEmpty()){
            etUser.setText(prefs.getString("usuario", ""));
        }

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

    @Override
    public void iniciarDetailActivity(UserAccount userAccount) {
        Toast.makeText(this, "Bem-vindo " + userAccount.getName(), Toast.LENGTH_SHORT).show();
        SharedPreferences prefs = getSharedPreferences("login_data", MODE_PRIVATE);
        if (!prefs.getString("usuario", "").equals(interactor.getUsuario().getLoginString())){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("usuario", interactor.getUsuario().getLoginString());
            editor.putString("senha", interactor.getUsuario().getSenhaString());
            editor.apply();
            Toast.makeText(this, "Salvei", Toast.LENGTH_SHORT).show();
        }
    }
}
