package com.msbm.bank.loginScreen;

import java.lang.ref.WeakReference;

interface LoginPresenterInput {
    void handleLogin(LoginResponse loginResponse);
}

public class LoginPresenter implements LoginPresenterInput {

    public static String TAG = LoginPresenter.class.getSimpleName();

    public WeakReference<LoginActivity> loginActivity;

    @Override
    public void handleLogin(LoginResponse loginResponse) {
        // TODO: build presenter to activity logic
    }
}
