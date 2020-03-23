package com.bankapp.loginScreen;


import android.content.Context;

interface LoginInteractorInput {
    public void doLogin(Context context, LoginRequest request);
    public void getSavedUser(Context context);
}

public class LoginInteractor implements LoginInteractorInput {

    public static String TAG = LoginInteractor.class.getSimpleName();
    public LoginPresenterInput output;
    public LoginWorkerInput aLoginWorkerInput;

    LoginResponse loginResponse;

    public LoginWorkerInput getLoginWorkerInput() {
        if (aLoginWorkerInput == null) return new LoginWorker();
        return aLoginWorkerInput;
    }

    @Override
    public void doLogin(final Context context, final LoginRequest request) {
        //TODO: doLogin
    }

    @Override
    public void getSavedUser(Context context) {
        //TODO: getSavedUser
    }

}
