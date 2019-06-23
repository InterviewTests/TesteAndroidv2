package com.bilulo.androidtest04.ui.login.worker;


import com.bilulo.androidtest04.data.api.ResponseListener;
import com.bilulo.androidtest04.data.api.login.LoginService;
import com.bilulo.androidtest04.data.model.response.LoginResponse;
import com.bilulo.androidtest04.ui.login.contract.LoginContract;
import com.bilulo.androidtest04.ui.login.interactor.LoginInteractor;

public class LoginWorker implements LoginContract.WorkerContract {
    public LoginInteractor interactor;
    @Override
    public void performLogin(String user, String password) {
        LoginService loginService = new LoginService();
        loginService.login(user, password, new ResponseListener<LoginResponse>() {
            @Override
            public void onResponseSuccess(LoginResponse response) {
                interactor.setLoginResponse(response, true);
            }

            @Override
            public void onResponseError() {
                interactor.setLoginResponse(null, false);
            }
        });
    }
}
