package com.msbm.bank.loginScreen;

import android.util.Log;

import java.lang.ref.WeakReference;

interface LoginPresenterInput {
    void handleLogin(LoginResponse loginResponse);
}

public class LoginPresenter implements LoginPresenterInput {

    public static String TAG = LoginPresenter.class.getSimpleName();

    public WeakReference<LoginActivity> loginActivity;

    @Override
    public void handleLogin(LoginResponse loginResponse) {
        if (loginResponse != null) {
            Log.d(TAG, loginResponse.toString());
        }
    }
}
