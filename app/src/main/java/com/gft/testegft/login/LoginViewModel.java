package com.gft.testegft.login;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.gft.testegft.base.BaseViewModel;
import com.gft.testegft.login.LoginValidation;

public class LoginViewModel extends BaseViewModel {

    private MutableLiveData<String> user = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();

    void validatePassword() {
        if (password.getValue() == null || password.getValue().isEmpty()) {
            Log.i("validatePass", "nula ou vazia");
        } else if (!LoginValidation.passwordValidation(password.getValue())) {
            Log.i("validatePass", "regex invalido");

        } else {
            Log.i("validatePass", "deu bom");
        }
    }

    public MutableLiveData<String> getUser() {
        return user;
    }

    public void setUser(String newUser) {
        user.setValue(newUser);
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void setPassword(MutableLiveData<String> password) {
        this.password = password;
    }
}
