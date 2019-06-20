package com.example.projectsantander.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectsantander.R;
import com.example.projectsantander.ui.extract.ScreenExtract;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {


    private EditText userName;
    private EditText password;
    private Button buttonLogin;

    private static final String PREF_SAVE = "MainActivityPreferences";
    private String login;
    private String senha;

    private SharedPreferences.OnSharedPreferenceChangeListener callbak = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        }
    };

    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPreferences = getSharedPreferences(PREF_SAVE, MODE_PRIVATE);
        login = sharedPreferences.getString("login", "login");

        sharedPreferences.registerOnSharedPreferenceChangeListener(callbak);

        SharedPreferences sharedPreferences1 = getSharedPreferences(PREF_SAVE, MODE_PRIVATE);
        senha = sharedPreferences.getString("senha", "senha");

        presenter = new LoginPresenter(this);

        loadUI();
        loadActions();


    }

    private void loadUI() {
        userName = findViewById(R.id.idUser);
        password = findViewById(R.id.idPassword);
        buttonLogin = findViewById(R.id.idButton);
    }

    private void loadActions() {
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent event) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    presenter.login(
                            userName.getText().toString(), password.getText().toString());
                    return true;

                }
                return false;
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.login(
                        userName.getText().toString(), password.getText().toString());

            }
        });

    }


    @Override
    public void showProgress(final boolean show) {

        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        userName.setVisibility(show ? View.GONE : View.VISIBLE);
        userName.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                userName.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });
        password.setVisibility(show ? View.VISIBLE : View.GONE);
        password.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                password.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });


    }


    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigationToExtract() {
        Intent intent = new Intent(this, ScreenExtract.class);
        finish();
        startActivity(intent);

    }


    @Override
    public Context getContext() {
        return LoginActivity.this;
    }
}
