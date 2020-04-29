package com.br.example.fakebank.presentations.viewModels.viewModelFactories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.br.example.fakebank.domains.services.StatementService;
import com.br.example.fakebank.presentations.handles.StatementHandle;
import com.br.example.fakebank.presentations.viewModels.StatementViewModel;

public class StatementViewModelFactory implements ViewModelProvider.Factory {

    private StatementService currencyService;
    private StatementHandle statementHandle;

    public StatementViewModelFactory(StatementService currencyService, StatementHandle statementHandle) {
        this.currencyService = currencyService;
        this.statementHandle = statementHandle;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(StatementViewModel.class)){
            return (T) new StatementViewModel(currencyService, statementHandle);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
