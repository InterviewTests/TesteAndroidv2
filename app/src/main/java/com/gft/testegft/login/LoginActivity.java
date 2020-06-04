package com.gft.testegft.login;

import android.view.MotionEvent;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.gft.testegft.R;
import com.gft.testegft.base.BaseActivity;
import com.gft.testegft.base.BaseViewModel;
import com.gft.testegft.databinding.ActivityMainBinding;

public class LoginActivity extends BaseActivity {

    private ActivityMainBinding binding;

    private LoginViewModel viewModel;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected BaseViewModel viewModel() {
        if(viewModel == null)
            viewModel = new LoginViewModel();
        return viewModel;
    }

    @Override
    protected void attachViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void observe() {
        viewModel.getPasswordError().observe(this, this::onPasswordError);
    }

    private void onPasswordError(String errorMessage) {
        binding.textInputPassword.setError(errorMessage);
        binding.textInputPassword.requestFocus();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v == binding.textInputPassword) {
                viewModel.validatePassword();
            }
        }

        return super.dispatchTouchEvent(event);
    }
}
