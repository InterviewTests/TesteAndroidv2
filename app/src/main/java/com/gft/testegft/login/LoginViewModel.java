package com.gft.testegft.login;

import androidx.lifecycle.MutableLiveData;

import com.gft.testegft.base.BaseViewModel;
import com.gft.testegft.login.enums.*;
import com.gft.testegft.login.utils.LoginValidation;

public class LoginViewModel extends BaseViewModel {

    private MutableLiveData<String> user = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<String> userError = new MutableLiveData<>();
    private MutableLiveData<String> passwordError = new MutableLiveData<>();

    void validateUser() {
        if (user.getValue() == null || user.getValue().isEmpty())
            userError.setValue(EnumUserErrors.NULL.desc);
        else if (!LoginValidation.userValidation(user.getValue()))
            userError.setValue(EnumUserErrors.INVALID.desc);
    }

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

    MutableLiveData<String> getUserError() {
        return userError;
    }
}
