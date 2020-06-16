package com.testeandroidv2.loginScreen;

import java.lang.ref.WeakReference;

interface LoginPresenterInput {
    void presentLoginData(LoginResponse response);
}


public class LoginPresenter implements LoginPresenterInput {

    public WeakReference<LoginActivityInput> output;

    @Override
    public void presentLoginData(LoginResponse response) {
        if(response.userAccount != null) {
            LoginViewModel loginViewModel = getLoginViewModel(response.userAccount);
            output.get().displayLoginData(loginViewModel);
        }else{
            output.get().displayLoginErro(response.error);
        }
    }

    private LoginViewModel getLoginViewModel(LoginModel loginModel){
        LoginViewModel loginViewModel;

        loginViewModel = new LoginViewModel();
        loginViewModel.name = loginModel.name;
        loginViewModel.agency = loginModel.agency;
        loginViewModel.bankAccount = loginModel.bankAccount;
        loginViewModel.balance = loginModel.balance;

        return loginViewModel;
    }


}
