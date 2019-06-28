package com.example.appbank.ui.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbank.R;
import com.example.appbank.data.remote.model.LoginRequest;
import com.example.appbank.data.remote.model.LoginResponse;
import com.example.appbank.ui.account.AccountActivity;

public class MainActivity extends AppCompatActivity implements LoginContract.LoginView {

    private Button btnLogin;
    private TextView textUser;
    private TextView textPassword;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    private LoginContract.LoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadUi();

        presenter = new LoginPresenter(this);

        textUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    presenter.validUserData(textUser.getText().toString());
                }
            }
        });

        textPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    presenter.validPasswordData(textPassword.getText().toString());
                }
            }
        });

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter.validPasswordData(textPassword.getText().toString())); {
                    LoginRequest loginRequest = new LoginRequest(textUser.getText().toString(), textPassword.getText().toString());
                    presenter.login(loginRequest);
                }
                //showProgress(true);

            }
        });

        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        if (preferences.contains("user")) {

            String user = preferences.getString("user", "");
            textUser.setText(user);

        } else {
            textUser.setText("User");
        }

        LoginContract.LoginPresenter presenter =  new LoginPresenter(this);

    }

    private void loadUi() {
        textPassword = findViewById(R.id.textPassword);
        textUser = findViewById(R.id.textUser);
    }

    private void sharedPreferencesUser() {

        //somente o app que vai conseguir salvar e ler o arquivo = modo 0
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        SharedPreferences.Editor editor = preferences.edit();

        if (textUser.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Preencha o usuário", Toast.LENGTH_LONG).show();
        } else {
            String user = textUser.getText().toString();
            editor.putString("user", user);
            editor.commit();
            textUser.setText(user);
        }
    }

    public void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        btnLogin.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
        btnLogin.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                btnLogin.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
            }
        });

        btnLogin.setVisibility(show ? View.VISIBLE : View.GONE);
        btnLogin.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                btnLogin.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void showError() {
        Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToList(LoginResponse loginResponse) {
        Intent intent = new Intent(MainActivity.this, AccountActivity.class);
        Bundle b = new Bundle();
        b.putInt("Id", loginResponse.getUserAccount().getUserId());
        b.putString("Name", loginResponse.getUserAccount().getName());
        b.putString("Agency", loginResponse.getUserAccount().getAgency());
        b.putString("BankAccount", loginResponse.getUserAccount().getBankAccount());
        b.putDouble("Balance", loginResponse.getUserAccount().getBalance());
        intent.putExtras(b);
        startActivity(intent);
        sharedPreferencesUser();
    }

    @Override
    public void errorUsername(String message) {
        textUser.setError(message);
    }

    @Override
    public void enabledButton(boolean b) {
        btnLogin.setEnabled(b);
    }

    @Override
    public void errorPassword(String s) {
        textPassword.setError(s);
    }
}
