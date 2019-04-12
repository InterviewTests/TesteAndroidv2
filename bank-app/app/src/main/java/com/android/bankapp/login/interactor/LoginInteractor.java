package com.android.bankapp.login.interactor;

import com.android.bankapp.login.presenter.LoginPresenter;
import com.android.bankapp.login.model.LoginRequest;

public class LoginInteractor implements LoginInteractorInput {


    public LoginPresenter output;

    @Override
    public void doLogin(String user, String password) {
        LoginRequest request = new LoginRequest(user, password);
        output.doLogin(request);
    }
}
