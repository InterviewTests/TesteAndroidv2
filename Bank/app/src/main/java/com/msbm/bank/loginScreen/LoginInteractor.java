package com.msbm.bank.loginScreen;

interface LoginInteractorInput {
    void doLogin(LoginRequest loginRequest);
}

public class LoginInteractor implements LoginInteractorInput {

    public static String TAG = LoginInteractor.class.getSimpleName();

    public LoginPresenterInput loginPresenterInput;

    @Override
    public void doLogin(LoginRequest loginRequest) {
        // TODO: build login interactor to present logic
    }
}
