package com.testeandroidv2.loginScreen;

import android.util.Log;

interface LoginInteractorInput {
    public void fetchLoginData(LoginRequest request);
}


public class LoginInteractor implements LoginInteractorInput {

    public static String TAG = LoginInteractor.class.getSimpleName();
    public LoginPresenterInput output;
    public LoginWorkerInput aLoginWorkerInput;

    public LoginWorkerInput getLoginWorkerInput() {
        if (aLoginWorkerInput == null) return new LoginWorker();
        return aLoginWorkerInput;
    }

    public void setLoginWorkerInput(LoginWorkerInput aLoginWorkerInput) {
        this.aLoginWorkerInput = aLoginWorkerInput;
    }

    @Override
    public void fetchLoginData(LoginRequest request) {
        aLoginWorkerInput = getLoginWorkerInput();
        LoginResponse LoginResponse = new LoginResponse();
        // Call the workers

        output.presentLoginData(LoginResponse);
    }
}
