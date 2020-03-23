package com.bankapp.loginScreen;

import com.bankapp.ErrorResponse;

import java.lang.ref.WeakReference;

interface LoginPresenterInput {
    public void responseLogin(LoginResponse response);
    public void responseErrorLogin(ErrorResponse error);
    public void responseErrorInvalidFields(LoginResponse response);
    public void responseSavedUser(LoginResponse response);
}


public class LoginPresenter implements LoginPresenterInput {

    public static String TAG = LoginPresenter.class.getSimpleName();
    public WeakReference<LoginActivityInput> output;

    @Override
    public void responseLogin(LoginResponse response) {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.userAccount = response.userAccount;
        output.get().signIn(loginViewModel);
    }

    @Override
    public void responseErrorLogin(ErrorResponse error) {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.error = error;
        output.get().errorSignIn(loginViewModel);
    }

    @Override
    public void responseErrorInvalidFields(LoginResponse response) {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.wrongPassword = response.wrongPassword;
        loginViewModel.wrongUser = response.wrongUser;
        output.get().errorUserOrPasswordInvalid(loginViewModel);
    }

    @Override
    public void responseSavedUser(LoginResponse response) {
        LoginViewModel loginViewModel = new LoginViewModel();
        LoginModel loginModel = new LoginModel(response.loginModel.user !=null ? response.loginModel.user : "", response.loginModel.password !=null ? response.loginModel.password : "");
        loginViewModel.loginModel = loginModel;
        output.get().bindLoginFields(loginViewModel);
    }
}
