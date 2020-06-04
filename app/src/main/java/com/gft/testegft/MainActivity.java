package com.gft.testegft;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.databinding.DataBindingUtil;

import com.gft.testegft.base.BaseActivity;
import com.gft.testegft.base.BaseViewModel;
import com.gft.testegft.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    private MainViewModel viewModel;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected BaseViewModel viewModel() {
        if(viewModel == null)
            viewModel = new MainViewModel();
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
