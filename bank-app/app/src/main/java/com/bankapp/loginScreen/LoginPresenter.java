package com.bankapp.loginScreen;

import com.bankapp.ErrorResponse;

import java.lang.ref.WeakReference;

interface LoginPresenterInput {
    public void responseLogin(LoginResponse response);
    public void responseErrorLogin(ErrorResponse error);
    public void responseErrorInvalidFields(LoginResponse response);
    public void responseSavedUser(LoginResponse response);
}


public class LoginPresenter implements LoginPresenterInput {

    public static String TAG = LoginPresenter.class.getSimpleName();
    public WeakReference<LoginActivityInput> output;

    @Override
    public void responseLogin(LoginResponse response) {
       //TODO: response login
    }

    @Override
    public void responseErrorLogin(ErrorResponse error) {
        //TODO:
    }

    @Override
    public void responseErrorInvalidFields(LoginResponse response) {
        //TODO:
    }

    @Override
    public void responseSavedUser(LoginResponse response) {
        //TODO:
    }
}
