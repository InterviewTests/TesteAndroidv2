package com.example.bankapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bankapp.model.user.UserAccount;
import com.example.bankapp.model.user.UserAccountModel;
import com.example.bankapp.ui.dashboard.DashboardActivity;
import com.example.bankapp.R;

public class LoginActivity extends AppCompatActivity implements LoginViewPresenter.loginView {
    private EditText editTextUser;
    private EditText editTextPassword;
    private Button buttonLogin;
    private LoginViewPresenter.loginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUser = (EditText) findViewById(R.id.editTextUser);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        presenter = new LoginPresenter(this);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
//                startActivity(intent);
                presenter.login(editTextUser.getText().toString(), editTextPassword.getText().toString());
            }
        });
    }

    @Override
    public void goToHome(UserAccount user) {
        Intent intentDash = new Intent(LoginActivity.this, DashboardActivity.class);
        intentDash.putExtra("userData", user);
        startActivity(intentDash);
    }

    @Override
    public void showErrorMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
