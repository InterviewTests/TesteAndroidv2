package com.br.example.fakebank.presentations.viewModels.viewModelFactories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.br.example.fakebank.domains.services.LoginService;
import com.br.example.fakebank.presentations.handles.LoginHandle;
import com.br.example.fakebank.presentations.viewModels.LoginViewModel;

public class LoginViewModelFactory implements ViewModelProvider.Factory {
    private LoginService mainService;
    private LoginHandle loginHandle;

    public LoginViewModelFactory(LoginService mainService, LoginHandle loginHandle) {
        this.mainService = mainService;
        this.loginHandle = loginHandle;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)){
            return (T) new LoginViewModel(mainService, loginHandle);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
