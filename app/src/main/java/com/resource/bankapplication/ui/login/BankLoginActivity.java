package com.resource.bankapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.resource.bankapplication.R;
import com.resource.bankapplication.config.App;
import com.resource.bankapplication.domain.UserAccount;
import com.resource.bankapplication.ui.entry.BankEntryActivity;

public class BankLoginActivity extends AppCompatActivity implements BankLoginContract.View {

    public static final String USER_ACCOUNT = "userAccount";
    private BankLoginContract.Presenter presenter;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private ProgressBar progressLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_login);

        presenter = new BankLoginPresenter(this);
        loadUi();
        loadActions();
        presenter.loadPreference();
    }

    private void loadActions() {
        editTextPassword.setOnEditorActionListener ((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                presenter.login(editTextUsername.getText().toString().trim(),
                        editTextPassword.getText().toString().trim());
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(editTextPassword.getWindowToken(), 0);
                return true;
            }
            return false;
        });
        buttonLogin.setOnClickListener(v ->
            presenter.login(editTextUsername.getText().toString().trim(),
                    editTextPassword.getText().toString().trim()));
    }

    private void loadUi() {
        editTextUsername = findViewById(R.id.edit_text_username);
        editTextPassword = findViewById(R.id.edit_text_password);
        buttonLogin = findViewById(R.id.button_login);
        progressLogin = findViewById(R.id.progress_login);
    }

    @Override
    public void navigationToHome(UserAccount userAccount) {
        Intent intent = new Intent(this, BankEntryActivity.class);
        intent.putExtra(USER_ACCOUNT, userAccount);
        startActivity(intent);
        finish();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(boolean show) {
        int shortAnimTime = getResources ().getInteger (android.R.integer.config_shortAnimTime);

        buttonLogin.setVisibility (show ? View.INVISIBLE : View.VISIBLE);
        buttonLogin.animate ().setDuration (shortAnimTime).alpha (
                show ? 0 : 1).setListener (new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                buttonLogin.setVisibility (show ? View.INVISIBLE : View.VISIBLE);
            }
        });

        progressLogin.setVisibility (show ? View.VISIBLE : View.GONE);
        progressLogin.animate ().setDuration (shortAnimTime).alpha (
                show ? 1 : 0).setListener (new AnimatorListenerAdapter () {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressLogin.setVisibility (show ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void setPreferences(UserAccount value) {
        editTextUsername.setText(value.getUsername());
        editTextPassword.setText(value.getPassword());
    }

    @Override
    public Context getContext() {
        return this;
    }
}
