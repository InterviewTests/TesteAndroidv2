package com.bank.testeandroidv2.loginScreen;

import java.lang.ref.WeakReference;

interface LoginPresenterInput {
    void presentLoginDataValidationForm(LoginResponse response);
    void processRequestLoginDataOnServer(UserAccount response);
    void processErrorRequest(String error);
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
    public void processRequestLoginDataOnServer(UserAccount response) {
        output.get().callNextScene(response);
    }

    @Override
    public void processErrorRequest(String error) {
        if(null == error) {
            String msg = "Null response from server";
            output.get().callApiError(msg);
        }
        else {
            output.get().callApiError(error);
        }
    }


}
