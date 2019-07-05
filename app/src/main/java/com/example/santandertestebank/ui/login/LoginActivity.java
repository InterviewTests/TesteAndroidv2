package com.example.santandertestebank.ui.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.santandertestebank.R;
import com.example.santandertestebank.model.models.UserAccountLogin;
import com.example.santandertestebank.ui.BankPaymentsActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private EditText editTextUser;
    private EditText editTextPassword;
    private Button buttonLogin;
    private ProgressBar loginProgressBar;

    private SharedPreferences sharedPref;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_USER = "user";
    public static final String KEY_PASSWORD = "password";

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        getSupportActionBar ().hide ();

        loadUI ();
        sharedPref = getSharedPreferences (SHARED_PREFS, Context.MODE_PRIVATE);

        this.editTextUser.setText (sharedPref.getString (KEY_USER, ""));
        this.editTextPassword.setText (sharedPref.getString (KEY_PASSWORD, ""));
        presenter = new LoginPresenter (this);

        this.buttonLogin.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                presenter.loginUser (
                        editTextUser.getText ().toString (),
                        editTextPassword.getText ().toString ());
                saveData (KEY_USER, KEY_PASSWORD);
            }
        });
    }

    public void saveData(String KEY_USER, String KEY_PASSWORD) {
        SharedPreferences.Editor editor = sharedPref.edit ();
        editor.putString (KEY_USER, editTextUser.getText ().toString ());
        editor.putString (KEY_PASSWORD, editTextPassword.getText ().toString ());
        editor.apply ();
    }

    private void loadUI() {
        editTextUser = findViewById (R.id.edit_text_user);
        editTextPassword = findViewById (R.id.edit_text_password);
        buttonLogin = findViewById (R.id.button_login);
        loginProgressBar = findViewById (R.id.login_progress);
        sharedPref = getSharedPreferences (SHARED_PREFS, Context.MODE_PRIVATE);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showToast(String s) {
        Toast.makeText (this, s, Toast.LENGTH_LONG).show ();
    }

    @Override
    public void showUserInfo(UserAccountLogin userAccountLogin) {
        Intent i = new Intent (getApplicationContext (), BankPaymentsActivity.class);
        i.putExtra ("keyLogin", userAccountLogin);
        startActivity (i);
        finish ();
    }

    @Override
    public void showProgressBar(final boolean show) {
        int shortAnimTime = getResources ().getInteger (android.R.integer.config_shortAnimTime);

        buttonLogin.setVisibility (show ? View.INVISIBLE : View.VISIBLE);
        buttonLogin.animate ().setDuration (shortAnimTime).alpha (show ? 0 : 1).setListener (
                new AnimatorListenerAdapter () {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        buttonLogin.setVisibility (show ? View.INVISIBLE : View.VISIBLE);
                    }
                });

        loginProgressBar.setVisibility (show ? View.INVISIBLE : View.VISIBLE);
        loginProgressBar.animate ().setDuration (shortAnimTime).alpha (show ? 0 : 1).setListener (
                new AnimatorListenerAdapter () {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        loginProgressBar.setVisibility (show ? View.INVISIBLE : View.VISIBLE);
                    }
                });
    }

}
