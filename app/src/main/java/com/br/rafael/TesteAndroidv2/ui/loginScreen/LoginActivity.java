package com.br.rafael.TesteAndroidv2.loginScreen;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.br.rafael.TesteAndroidv2.R;
import com.br.rafael.TesteAndroidv2.data.model.LoginResponse;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;


interface LoginActivityInput {
    void displayHomeMetaData(LoginResponse response);
    void displayMensagem(String mensage);
    void showProgressBar();
    void hideProgressBar();
}

public class LoginActivity extends AppCompatActivity implements LoginActivityInput {

    TextInputEditText edtUser;
    TextInputEditText edtPassword;
    ProgressBar progressBar;
    Button btnLogar;

    protected LoginInteractorInput output;
    protected LoginRouter router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginConfigurator.INSTANCE.configure(this);
        initView();
        initListener();
    }

    private void initView() {

        edtUser = findViewById(R.id.edt_user);
        edtPassword = findViewById(R.id.edt_password);
        progressBar = findViewById(R.id.progressBar);
        btnLogar = findViewById(R.id.btnLogar);
    }

    private void initListener() {

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareRequest();
            }
        });
    }

    @Override
    public void displayHomeMetaData(LoginResponse response) {
        router.statementScreen(response);
    }

    @Override
    public void displayMensagem(String mensage) {
        Snackbar.make(findViewById(android.R.id.content),
                mensage, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {

        progressBar.setVisibility(View.GONE);

    }

    public void prepareRequest() {

        LoginRequest request = new  LoginRequest();
        request.setLogin(edtUser.getText().toString());
        request.setPassword(edtPassword.getText().toString());
        output.fetchHomeMetaData(request);
    }
}
