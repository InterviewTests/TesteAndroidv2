package com.br.example.fakebank.presentations.viewModels.viewModelFactories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.br.example.fakebank.domains.services.MainService;
import com.br.example.fakebank.presentations.handles.MainHandle;
import com.br.example.fakebank.presentations.viewModels.MainViewModel;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    private MainService mainService;
    private MainHandle mainHandle;

    public MainViewModelFactory(MainService mainService, MainHandle mainHandle) {
        this.mainService = mainService;
        this.mainHandle = mainHandle;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)){
            return (T) new MainViewModel(mainService, mainHandle);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
