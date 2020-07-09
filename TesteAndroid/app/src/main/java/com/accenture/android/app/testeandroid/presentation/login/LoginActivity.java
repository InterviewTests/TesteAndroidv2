package com.accenture.android.app.testeandroid.presentation.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.accenture.android.app.testeandroid.databinding.ActivityLoginBinding;

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
}
