package com.example.testesantander;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import Models.UserAccount;
import Interactors.LoginInteractor;
import Presenters.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginActivityInput{
    private EditText etUser;
    private EditText etPassword;
    private LoginInteractor interactor;
    private LoginPresenter presenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        etUser = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPassword);
        Button btLogin = findViewById(R.id.btLogin);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);

        presenter = new LoginPresenter(this);
        interactor = new LoginInteractor(presenter);

        SharedPreferences prefs = getSharedPreferences("login_data", MODE_PRIVATE);
        if (!prefs.getString("usuario", "").isEmpty()){
            etUser.setText(prefs.getString("usuario", ""));
        }

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interactor.criaUsuario(etUser.getText().toString(), etPassword.getText().toString());
                if (interactor.validaUsuario()) {
                    startLoadingBar();
                    interactor.logar();
                } else {
                    Toast.makeText(view.getContext(), "Usuário e/ou Senha inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        etPassword.setText("");
    }

    @Override
    public void injetarDependencia(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void iniciarDetailActivity(UserAccount userAccount) {
        SharedPreferences prefs = getSharedPreferences("login_data", MODE_PRIVATE);
        if (!prefs.getString("usuario", "").equals(interactor.getUsuario().getLoginString())){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("usuario", interactor.getUsuario().getLoginString());
            editor.putString("senha", interactor.getUsuario().getSenhaString());
            editor.apply();
        }
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("userAccount", userAccount);
        startActivity(intent);
        Toast.makeText(this, "Bem-vindo " + userAccount.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startLoadingBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoadingBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
