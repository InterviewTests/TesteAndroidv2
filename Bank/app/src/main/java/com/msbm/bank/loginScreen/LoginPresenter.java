package com.msbm.bank.loginScreen;

import android.util.Log;

import java.lang.ref.WeakReference;

interface LoginPresenterInput {
    void handleLogin(LoginResponse loginResponse);
}

public class LoginPresenter implements LoginPresenterInput {

    public static String TAG = LoginPresenter.class.getSimpleName();

    public WeakReference<LoginActivityInput> loginActivity;

    @Override
    public void handleLogin(LoginResponse loginResponse) {
        if (loginResponse != null) {
            loginActivity.get().saveUserAccountSharedPreferences(loginResponse.userAccount);
            loginActivity.get().nextScreen();
        }
    }
}
