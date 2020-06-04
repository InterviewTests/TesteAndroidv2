package com.gft.testegft.login;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.gft.testegft.R;
import com.gft.testegft.base.BaseActivity;
import com.gft.testegft.databinding.ActivityMainBinding;
import com.gft.testegft.util.ViewModelFactory;

import javax.inject.Inject;

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
    }

    private void onUserError(String errorMessage) {
        binding.textInputUser.setError(errorMessage);
        binding.textInputUser.requestFocus();
    }

    private void onPasswordError(String errorMessage) {
        binding.textInputPassword.setError(errorMessage);
        binding.textInputPassword.requestFocus();
    }

    public void login(View view) {
        viewModel.login();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();

            if (v == binding.textInputPassword)
                viewModel.validatePassword();
            else if (v == binding.textInputUser)
                viewModel.validateUser();
        }

        return super.dispatchTouchEvent(event);
    }
}
