package com.br.example.fakebank.presentations.viewModels.viewModelFactories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.br.example.fakebank.presentations.handles.CurrencyHandle;
import com.br.example.fakebank.domains.models.CurrencyModel;
import com.br.example.fakebank.presentations.viewModels.CurrencyViewModel;
import com.br.example.fakebank.presentations.viewModels.MainViewModel;

public class CurrencyViewModelFactory implements ViewModelProvider.Factory {

    private CurrencyModel currencyModel;
    private CurrencyHandle currencyHandle;

    public CurrencyViewModelFactory(CurrencyModel currencyModel, CurrencyHandle currencyHandle) {
        this.currencyModel = currencyModel;
        this.currencyHandle = currencyHandle;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)){
            return (T) new CurrencyViewModel(currencyModel, currencyHandle);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
