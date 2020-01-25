package com.msbm.bank.loginScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.msbm.bank.R;
import com.msbm.bank.entities.User;

public class LoginActivity extends AppCompatActivity implements LoginInteractorInput {

    public static String TAG = LoginActivity.class.getSimpleName();

    protected LoginInteractor loginInteractor;

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        bindViews();

        LoginConfigurator.INSTANCE.configure(this);
    }

    private void bindViews() {
        edtUsername = findViewById(R.id.idUsername);
        edtUsername.setText("test_user");
        edtPassword = findViewById(R.id.idPassword);
        edtPassword.setText("Test@1");

        btnLogin = findViewById(R.id.idButtonLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUsername(edtUsername.getText().toString());
                user.setPassword(edtPassword.getText().toString());

                LoginRequest request = new LoginRequest();
                request.user = user;

                doLogin(request);
            }
        });
    }

    @Override
    public void doLogin(LoginRequest loginRequest) {
        this.loginInteractor.doLogin(loginRequest);
    }
}
