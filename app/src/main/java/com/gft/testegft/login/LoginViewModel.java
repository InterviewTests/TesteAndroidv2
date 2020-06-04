package com.gft.testegft.login;

import androidx.lifecycle.MutableLiveData;

import com.gft.testegft.base.BaseViewModel;
import com.gft.testegft.login.enums.EnumPasswordErrors;

public class LoginViewModel extends BaseViewModel {

    private MutableLiveData<String> user = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<String> passwordError = new MutableLiveData<>();

    void validatePassword() {
        if (password.getValue() == null || password.getValue().isEmpty())
            passwordError.setValue(EnumPasswordErrors.NULL.desc);
        else if (!LoginValidation.passwordValidation(password.getValue()))
            passwordError.setValue(EnumPasswordErrors.INVALID.desc);
    }

    public MutableLiveData<String> getUser() {
        return user;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    MutableLiveData<String> getPasswordError() {
        return passwordError;
    }
}
