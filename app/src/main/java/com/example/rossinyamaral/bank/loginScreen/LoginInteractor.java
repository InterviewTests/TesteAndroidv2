package com.example.rossinyamaral.bank.loginScreen;

import android.util.Log;

import com.example.rossinyamaral.bank.BankApi;
import com.example.rossinyamaral.bank.BankApplication;
import com.example.rossinyamaral.bank.model.UserAccountModel;

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
        final LoginResponse LoginResponse = new LoginResponse();
        // Call the workers
        BankApplication.getInstance().bankApi.login(request.user, request.password,
                new BankApi.ApiCallback<UserAccountModel>() {
                    @Override
                    public void onSuccess(UserAccountModel object) {
                        LoginResponse.userAccountModel = object;
                        output.presentLoginData(LoginResponse);
                    }

                    @Override
                    public void onError(BankApi.ErrorResponse message) {

                    }
                });

    }
}
