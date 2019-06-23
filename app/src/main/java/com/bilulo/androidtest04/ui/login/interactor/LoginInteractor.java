package com.bilulo.androidtest04.ui.login.interactor;

import com.bilulo.androidtest04.data.model.response.LoginResponse;
import com.bilulo.androidtest04.ui.login.contract.LoginContract;
import com.bilulo.androidtest04.ui.login.worker.LoginWorker;

public class LoginInteractor implements LoginContract.InteractorContract {
    public LoginContract.PresenterContract presenter;
    public LoginWorker worker;

    @Override
    public void performLogin(String user, String password) {
        worker.performLogin(user,password);
    }

    @Override
    public void setLoginResponse(LoginResponse response, boolean isSucessful) {
        presenter.setLoginResponse(response, isSucessful);
    }

}
