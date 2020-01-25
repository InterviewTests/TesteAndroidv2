package com.msbm.bank.loginScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.msbm.bank.BaseActivity;
import com.msbm.bank.R;
import com.msbm.bank.accountDetailScreen.AccountDetailActivity;
import com.msbm.bank.entities.User;
import com.msbm.bank.entities.UserAccount;

interface LoginActivityInput {
    void saveUserAccountSharedPreferences(UserAccount userAccount);
    void nextScreen();

    void emptyUsername();
    void emptyPassword();
    void invalidUsername();
    void invalidPassword();
    void invalidCredentials();
}

public class LoginActivity extends BaseActivity implements LoginActivityInput {

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
        edtUsername.setText("test_user@gmail.com");
        edtPassword = findViewById(R.id.idPassword);
        edtPassword.setText("Test@1");

        btnLogin = findViewById(R.id.idButtonLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress(getString(R.string.loading_title), getString(R.string.loading_message));

                User user = new User();
                user.setUsername(edtUsername.getText().toString());
                user.setPassword(edtPassword.getText().toString());

                LoginRequest request = new LoginRequest();
                request.user = user;

                loginInteractor.doLogin(request);
            }
        });
    }

    @Override
    public void saveUserAccountSharedPreferences(UserAccount userAccount) {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.user_account_id), userAccount.getUserId());
        editor.putString(getString(R.string.user_account_id), userAccount.getName());
        editor.putString(getString(R.string.user_account_id), userAccount.getBankAccount());
        editor.putString(getString(R.string.user_account_id), userAccount.getAgency());
        editor.putString(getString(R.string.user_account_id), userAccount.getBalance());
        editor.commit();
    }

    @Override
    public void nextScreen() {
        dismissProgress();

        Intent intent = new Intent(this, AccountDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void emptyUsername() {
        dismissProgress();
        showAlert(getString(R.string.alert_title_warning), getString(R.string.empty_username));
    }

    @Override
    public void emptyPassword() {
        dismissProgress();
        showAlert(getString(R.string.alert_title_warning), getString(R.string.empty_password));
    }

    @Override
    public void invalidUsername() {
        dismissProgress();
        showAlert(getString(R.string.alert_title_warning), getString(R.string.invalid_username));
    }

    @Override
    public void invalidPassword() {
        dismissProgress();
        showAlert(getString(R.string.alert_title_warning), getString(R.string.invalid_password));
    }

    @Override
    public void invalidCredentials() {
        dismissProgress();
        showAlert(getString(R.string.alert_title_warning), getString(R.string.invalid_credentials));
    }
}
