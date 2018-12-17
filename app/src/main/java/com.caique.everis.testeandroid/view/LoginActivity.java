package com.caique.everis.testeandroid.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.caique.everis.testeandroid.R;
import com.caique.everis.testeandroid.model.Login;
import com.caique.everis.testeandroid.model.LoginResponse;
import com.caique.everis.testeandroid.viewmodel.BankViewModel;


public class LoginActivity extends AppCompatActivity {

    private BankViewModel bankViewModel;
    private Button loginButton;
    private EditText loginEditText;
    private EditText passwordEditText;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        bankViewModel = ViewModelProviders.of(this).get(BankViewModel.class);

        context = this;
        loginButton = findViewById(R.id.login_button);
        loginEditText = findViewById(R.id.user);
        passwordEditText = findViewById(R.id.password);
        getLoginDao();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(loginEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });
    }

    private void login(String user, String password) {
        if (!user.isEmpty() && !password.isEmpty()) {
            if (BankViewModel.isValidPassword(password)) {
                showLoading(true);
                final Login login = new Login();
                login.setUser(user);
                login.setPassword(password);
                bankViewModel.getLoginResponse(login).observe(this, new Observer<LoginResponse>() {
                    @Override
                    public void onChanged(@Nullable LoginResponse loginResponse) {
                        Intent intent = new Intent(context, AccountActivity.class);
                        intent.putExtra("user", loginResponse);
                        startActivity(intent);
                        showLoading(false);
                    }
                });
            } else {
                Toast.makeText(this, getResources().getText(R.string.invalid_password), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, getResources().getText(R.string.user_missing), Toast.LENGTH_LONG).show();
        }
    }

    private void showLoading(Boolean show) {
        ConstraintLayout constraintLayout = findViewById(R.id.loading);
        if (show) {
            constraintLayout.setVisibility(View.VISIBLE);
        } else {
            constraintLayout.setVisibility(View.GONE);
        }
    }

    private void getLoginDao() {
        bankViewModel.getLoginDao().observe(this, new Observer<Login>() {
            @Override
            public void onChanged(@Nullable Login login) {
                if (login != null) {
                    loginEditText.setText(login.getUser());
                    passwordEditText.setText(login.getPassword());
                }
            }
            });
    }
}
