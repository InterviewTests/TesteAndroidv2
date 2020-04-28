package com.br.example.fakebank.presentations.viewModels;

import androidx.lifecycle.ViewModel;

import com.br.example.fakebank.domains.services.CurrencyService;
import com.br.example.fakebank.presentations.handles.CurrencyHandle;

import io.reactivex.disposables.CompositeDisposable;

public class CurrencyViewModel extends ViewModel {

    private CurrencyService currencyService;
    private CurrencyHandle currencyHandle;

    private CompositeDisposable disposable = new CompositeDisposable();

    public CurrencyViewModel(CurrencyService currencyService, CurrencyHandle currencyHandle) {
        this.currencyService = currencyService;
        this.currencyHandle = currencyHandle;
    }
}
