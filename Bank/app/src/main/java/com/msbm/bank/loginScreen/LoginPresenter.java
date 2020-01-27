package com.msbm.bank.loginScreen;

import java.lang.ref.WeakReference;

interface LoginPresenterInput {
    void handleLogin(LoginResponse loginResponse);

    void emptyUsername();
    void emptyPassword();
    void invalidUsername();
    void invalidPassword();
    void invalidCredentials();
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

    @Override
    public void emptyUsername() {
        loginActivity.get().emptyUsername();
    }

    @Override
    public void emptyPassword() {
        loginActivity.get().emptyPassword();
    }

    @Override
    public void invalidUsername() {
        loginActivity.get().invalidUsername();
    }

    @Override
    public void invalidPassword() {
        loginActivity.get().invalidPassword();
    }

    @Override
    public void invalidCredentials() {
        loginActivity.get().invalidCredentials();
    }
}
