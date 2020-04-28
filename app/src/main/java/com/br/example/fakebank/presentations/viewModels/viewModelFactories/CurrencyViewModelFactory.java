package com.br.example.fakebank.presentations.viewModels.viewModelFactories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.br.example.fakebank.domains.services.CurrencyService;
import com.br.example.fakebank.presentations.handles.CurrencyHandle;
import com.br.example.fakebank.presentations.viewModels.CurrencyViewModel;

public class CurrencyViewModelFactory implements ViewModelProvider.Factory {

    private CurrencyService currencyService;
    private CurrencyHandle currencyHandle;

    public CurrencyViewModelFactory(CurrencyService currencyService, CurrencyHandle currencyHandle) {
        this.currencyService = currencyService;
        this.currencyHandle = currencyHandle;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CurrencyViewModel.class)){
            return (T) new CurrencyViewModel(currencyService, currencyHandle);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
