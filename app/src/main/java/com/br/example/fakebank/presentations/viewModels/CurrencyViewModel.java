package com.br.example.fakebank.presentations.viewModels;

import androidx.lifecycle.ViewModel;

import com.br.example.fakebank.presentations.handles.CurrencyHandle;
import com.br.example.fakebank.domains.models.CurrencyModel;

public class CurrencyViewModel extends ViewModel {

    private CurrencyModel currencyModel;
    private CurrencyHandle currencyHandle;

    public CurrencyViewModel(CurrencyModel currencyModel, CurrencyHandle currencyHandle) {
        this.currencyModel = currencyModel;
        this.currencyHandle = currencyHandle;
    }
}
