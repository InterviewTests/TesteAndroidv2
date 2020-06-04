package com.gft.testegft.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.gft.testegft.R;
import com.gft.testegft.base.BaseActivity;
import com.gft.testegft.databinding.ActivityMainBinding;
import com.gft.testegft.login.data.LoginResponse;
import com.gft.testegft.statements.StatementsActivity;
import com.gft.testegft.util.ViewModelFactory;
import com.google.gson.Gson;

import javax.inject.Inject;

import static com.gft.testegft.util.Constants.LOGIN_RESPONSE_FLAG;

public class LoginActivity extends BaseActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    private LoginViewModel viewModel;

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachViewModel();
        observe();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    protected void attachViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
    }

    protected void observe() {
        viewModel.getPasswordError().observe(this, this::onPasswordError);
        viewModel.getUserError().observe(this, this::onUserError);
        viewModel.getRequestError().observe(this, this::showToast);
        viewModel.getLoginResponse().observe(this, this::onLoginSuccess);
    }

    private void onUserError(String errorMessage) {
        binding.textInputUser.setError(errorMessage);
        binding.textInputUser.requestFocus();
    }

    private void onPasswordError(String errorMessage) {
        binding.textInputPassword.setError(errorMessage);
        binding.textInputPassword.requestFocus();
    }

    private void showToast(String value) {
        Toast.makeText(getApplicationContext(), value, Toast.LENGTH_LONG).show();
    }

    private void onLoginSuccess(LoginResponse loginResponse) {
        Intent intent = new Intent(this, StatementsActivity.class);
        intent.putExtra(LOGIN_RESPONSE_FLAG, new Gson().toJson(loginResponse));
        startActivity(intent);
    }

    public void login(View view) {
        viewModel.login();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();

            // valida a senha quando o campo perde o foco
            if (v == binding.textInputPassword)
                viewModel.validatePassword();

            // valida o user quando o campo perde o foco
            else if (v == binding.textInputUser)
                viewModel.validateUser();
        }

        return super.dispatchTouchEvent(event);
    }
}
