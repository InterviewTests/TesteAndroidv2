package com.bank.testeandroidv2.loginScreen;

import android.util.Log;


interface LoginInteractorInput {

    void validateLoginData(LoginRequest request);

    void requestLoginDataOnServer(LoginRequest request);
}

interface LoginCallback {
    void onResponse(LoginResponse response);

    void onFailure(LoginResponse response);
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
    public void validateLoginData(LoginRequest request) {
        Log.e(TAG, "In method fetchLoginData");
        aLoginWorkerInput = getLoginWorkerInput();
        LoginResponse loginResponse = new LoginResponse();
        Log.e(TAG, request.toString());
        loginResponse.status = aLoginWorkerInput.verifyTextFieldsAreCorrect(request);
        Log.e(TAG, loginResponse.toString());
        output.presentLoginDataValidationForm(loginResponse);
    }

    @Override
    public void requestLoginDataOnServer(LoginRequest request) {
        Log.e(TAG, "In method requestLoginDataOnServer");
        aLoginWorkerInput.postLogin(request, new LoginCallback() {
            @Override
            public void onResponse(LoginResponse loginResponse) {
                output.processRequestLoginDataOnServer(loginResponse);
            }

            @Override
            public void onFailure(LoginResponse loginResponse) {
                output.processErrorRequest(loginResponse);
            }
        });
    }
}
