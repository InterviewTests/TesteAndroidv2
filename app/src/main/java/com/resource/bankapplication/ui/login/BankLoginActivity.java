package com.resource.bankapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.resource.bankapplication.R;
import com.resource.bankapplication.domain.UserAccount;
import com.resource.bankapplication.ui.entry.BankEntryActivity;

public class BankLoginActivity extends AppCompatActivity implements BankLoginContract.View {

    public static final String USER_ACCOUNT = "userAccount";
    private BankLoginContract.Presenter presenter;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_login);

        presenter = new BankLoginPresenter(this);
        loadUi();
        loadActions();
    }

    private void loadActions() {
        buttonLogin.setOnClickListener(v -> {
            presenter.login(editTextUsername.getText().toString().trim(),
                    editTextPassword.getText().toString().trim());
        });
    }

    private void loadUi() {
        editTextUsername = findViewById(R.id.edit_text_username);
        editTextPassword = findViewById(R.id.edit_text_password);
        buttonLogin = findViewById(R.id.button_login);
    }

    @Override
    public void navigationToHome(UserAccount userAccount) {
        Intent intent = new Intent(this, BankEntryActivity.class);
        intent.putExtra(USER_ACCOUNT, userAccount);
        startActivity(intent);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
