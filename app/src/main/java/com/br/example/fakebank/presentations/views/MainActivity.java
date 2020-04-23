package com.br.example.fakebank.presentations.views;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.br.example.fakebank.BaseActivity;
import com.br.example.fakebank.R;
import com.br.example.fakebank.databinding.ActivityMainBinding;
import com.br.example.fakebank.presentations.viewModels.viewModelFactories.MainViewModelFactory;
import com.br.example.fakebank.presentations.handles.MainHandle;
import com.br.example.fakebank.domains.models.MainModel;
import com.br.example.fakebank.presentations.viewModels.MainViewModel;

public class MainActivity extends BaseActivity implements MainHandle {

    private ActivityMainBinding activityMainBinding;
    private MainViewModelFactory mainViewModelFactory;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModelFactory = new MainViewModelFactory(new MainModel(),this);
        mainViewModel = new ViewModelProvider(this,mainViewModelFactory).get(MainViewModel.class);

    }

    @Override
    public void actionLogin() {

    }
}
