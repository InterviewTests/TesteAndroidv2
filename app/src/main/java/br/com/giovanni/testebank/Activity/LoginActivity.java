package br.com.giovanni.testebank.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.giovanni.testebank.Interactor.LoginInteractor;
import br.com.giovanni.testebank.Presenter.PresenterLogin;
import br.com.giovanni.testebank.R;
import br.com.giovanni.testebank.Model.UserAccount;

public class LoginActivity extends AppCompatActivity implements IItentLogin {

    public EditText getUser;
    public EditText getPassword;
    public Button btnLogin;
    public TextView textViewJson;
    private String getUserConvert;
    private String getPasswordConvert;
    private LoginInteractor loginInteractor;
    private PresenterLogin presenterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getUser = findViewById(R.id.editTextUserId);
        getPassword = findViewById(R.id.editTextPasswordId);
        btnLogin = findViewById(R.id.buttonLoginId);
        textViewJson = findViewById(R.id.txtNameId);
        presenterLogin = new PresenterLogin(this);
        loginInteractor = new LoginInteractor(presenterLogin);
        SharedPreferences sharedPreferences = getSharedPreferences("login_data", MODE_PRIVATE);

        if (sharedPreferences.contains("usuario")) {
            getUser.setText(sharedPreferences.getString("usuario", ""));
        }
        btnLoginOnClick();

    }

    public void btnLoginOnClick() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getUserConvert = getUser.getText().toString();
                getPasswordConvert = getPassword.getText().toString().trim();

                loginInteractor.getLoginAcces(getUserConvert, getPasswordConvert);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPassword.setText("");
    }

    @Override
    public void changeScreen(UserAccount userAccount) {

        SharedPreferences prefs = getSharedPreferences("login_data", MODE_PRIVATE);
        if (!prefs.getString("usuario", "").equals(getUserConvert)) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("usuario", getUserConvert);
            editor.apply();
        }

        Intent setIntent = new Intent(this, DetailActivity.class);
        setIntent.putExtra("userAccount_key", userAccount);
        startActivity(setIntent);
    }

}