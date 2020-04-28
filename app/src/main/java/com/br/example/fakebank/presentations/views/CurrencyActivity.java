package com.br.example.fakebank.presentations.views;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.br.example.fakebank.BaseActivity;
import com.br.example.fakebank.R;
import com.br.example.fakebank.databinding.ActivityCurrencyBinding;
import com.br.example.fakebank.domains.services.CurrencyService;
import com.br.example.fakebank.presentations.handles.CurrencyHandle;
import com.br.example.fakebank.presentations.viewModels.CurrencyViewModel;
import com.br.example.fakebank.presentations.viewModels.viewModelFactories.CurrencyViewModelFactory;

public class CurrencyActivity extends BaseActivity implements CurrencyHandle {

    private ActivityCurrencyBinding activityCurrencyBinding;
    private CurrencyViewModelFactory currencyViewModelFactory;
    private CurrencyViewModel currencyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCurrencyBinding = DataBindingUtil.setContentView(this, R.layout.activity_currency);

        currencyViewModelFactory = new CurrencyViewModelFactory(new CurrencyService(),this);
        currencyViewModel = new ViewModelProvider(this, currencyViewModelFactory).get(CurrencyViewModel.class);
    }

    @Override
    public void actionLogout() {

    }
}
