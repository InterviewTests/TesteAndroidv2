package com.casasw.bankapp;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

interface LoginInteractorInput {
    void fetchLoginData(LoginRequest request);
}


public class LoginInteractor implements LoginInteractorInput {

    public static String TAG = LoginInteractor.class.getSimpleName();
    public LoginPresenterInput output;
    public LoginWorkerInput aLoginWorkerInput;

    public LoginWorkerInput getLoginWorkerInput() {
        if (aLoginWorkerInput == null) return new LoginWorker();
        return aLoginWorkerInput;
    }

     void setLoginWorkerInput(LoginWorkerInput aLoginWorkerInput) {
        this.aLoginWorkerInput = aLoginWorkerInput;
    }

    @Override
    public void fetchLoginData(LoginRequest request) {
        Ion.with(request.getContext())
                .load("https://bank-app-test.herokuapp.com/api/login")
                .setBodyParameter("user", "test_user")
                .setBodyParameter("password", "Test@1")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        LoginResponse loginResponse = new LoginResponse();
                        loginResponse.setLoginJSON(result);
                        output.presentLoginData(loginResponse);

                    }
                });

    }
}
