package com.projeto.testedevandroidsantander.ui.loginScreen;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.projeto.santander.R;



interface LoginActivityInput {
    void displayLoginMetaData(LoginViewModel viewModel);
    void showProgressBar();
    void hideProgressBar();
    void showMessageLoginError(String message);
    void setLoginSharedPreferences(String login);
    String getMessageLoginError();
    String getMessageCpfError();
    String getMessageSenhaError();
    Context getContext();
}

public class LoginActivity extends AppCompatActivity implements LoginActivityInput {

    LoginInteractorInput output;
    LoginRouter router;
    ProgressBar progressBar;
    EditText loginEditText;
    EditText senhaEditText;
    Button logarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        LoginConfigurator.INSTANCE.configure(this);

        output.getUserSharedPreferences();
    }

    private void init() {
        loginEditText = findViewById(R.id.loginEditText);
        senhaEditText = findViewById(R.id.senhaEditText);
        progressBar = findViewById(R.id.progressBar);
        logarButton = findViewById(R.id.logarButton);
        logarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRequest();
            }
        });
    }

    public void loginRequest() {
        LoginRequest request = new LoginRequest();
        request.setLogin(loginEditText.getText().toString());
        request.setPassword(senhaEditText.getText().toString());
        output.fetchLoginMetaData(request);
    }


    @Override
    public void displayLoginMetaData(LoginViewModel viewModel) {
        router.irParaMainActivity(viewModel);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessageLoginError(String message) {
        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.appCompatImageView), message, Snackbar.LENGTH_SHORT);
        mySnackbar.show();
    }

    @Override
    public void setLoginSharedPreferences(String login) {
        loginEditText.setText(login);
    }

    @Override
    public String getMessageLoginError() {
        return getResources().getString(R.string.message_login_senha_invalido);
    }

    @Override
    public String getMessageCpfError() {
        return getResources().getString(R.string.message_cpf_email_invalido);
    }

    @Override
    public String getMessageSenhaError() {
        return getResources().getString(R.string.message_senha_invalida);
    }

    @Override
    public Context getContext() {
        return LoginActivity.this;
    }

}
