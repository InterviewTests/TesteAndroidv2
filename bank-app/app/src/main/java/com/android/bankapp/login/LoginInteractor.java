package com.android.bankapp.login;

public class LoginInteractor implements LoginInteractorInput {


    public LoginPresenter output;

    @Override
    public void doLogin(String user, String password) {
        LoginRequest request = new LoginRequest(user, password);
        output.doLogin(request);
    }
}
