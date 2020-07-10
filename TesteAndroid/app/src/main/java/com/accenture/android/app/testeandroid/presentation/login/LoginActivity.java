package com.accenture.android.app.testeandroid.presentation.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.accenture.android.app.testeandroid.databinding.ActivityLoginBinding;
import com.accenture.android.app.testeandroid.presentation.main.MainActivity;
import com.google.android.material.snackbar.Snackbar;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private final static String TAG = "CustomLog - " + LoginActivity.class.getSimpleName();

    private ActivityLoginBinding binding;

    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityLoginBinding.inflate(getLayoutInflater());

        this.presenter = new LoginPresenter(this, this.getLifecycle(), this);

        setContentView(this.binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.initEvents();
    }

    private void initEvents() {
        this.binding.btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = binding.edtUser.getText().toString();
                String password = binding.edtPassword.getText().toString();

                presenter.efetuarLogin(user, password);
            }
        });
    }

    @Override
    public void setLoading() {
        this.binding.pgbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void unsetLoading() {
        this.binding.pgbLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setContent() {
        this.binding.btnLogar.setEnabled(true);
    }

    @Override
    public void unsetContent() {
        this.binding.btnLogar.setEnabled(false);
    }

    @Override
    public void setFeedback(String message) {
        Snackbar.make(this.binding.getRoot(), message, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

        startActivity(intent);

        this.finish();
    }
}
