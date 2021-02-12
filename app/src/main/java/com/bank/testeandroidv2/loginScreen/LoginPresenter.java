package com.bank.testeandroidv2.loginScreen;

import java.lang.ref.WeakReference;

interface LoginPresenterInput {
    void presentLoginDataValidationForm(LoginResponse response);
    void processRequestLoginDataOnServer(LoginResponse response);
    void processErrorRequest(LoginResponse response);
}


public class LoginPresenter implements LoginPresenterInput {

    public static String TAG = LoginPresenter.class.getSimpleName();

    //weak var output: LoginActivityInput!
    public WeakReference<LoginActivityInput> output;


    @Override
    public void presentLoginDataValidationForm(LoginResponse response) {
        // Log.e(TAG, "presentLoginData() called with: response = [" + response + "]");
        //Do your decoration or filtering here
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.status = response.status;

        output.get().processLoginDataForm(loginViewModel);
    }

    @Override
    public void processRequestLoginDataOnServer(LoginResponse response) {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.userAccount = response.userAccount;
        output.get().callNextScene(loginViewModel);
    }

    @Override
    public void processErrorRequest(LoginResponse response) {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.userAccount = response.userAccount;
        if(null == response) {
            String msg = "Null response from server";
            loginViewModel.error = msg;
            output.get().callApiError(loginViewModel);
        }
        else {
            loginViewModel.error = response.error;
            output.get().callApiError(loginViewModel);
        }
    }


}
