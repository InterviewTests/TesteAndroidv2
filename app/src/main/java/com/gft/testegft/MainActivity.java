package com.gft.testegft;

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
}
