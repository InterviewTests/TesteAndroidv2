package com.test.banktest.loginScreen;


import java.lang.ref.WeakReference;

interface LoginPresenterInput {
    public void presentLoginData(LoginResponse response);
    public void presentLastUser(LoginResponse response);
}

public class LoginPresenter implements LoginPresenterInput {

    public static String TAG = LoginPresenter.class.getSimpleName();

    //weak var output: HomePresenterOutput!
    public WeakReference<LoginActivityInput> output;


    @Override
    public void presentLoginData(LoginResponse response) {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.userError = response.isUserValid ? null : "Usuário inválido. Informe o seu CPF ou e-mail";
        loginViewModel.passwordError = response.isPasswordValid ? null : "Senha inválida. Informe uma senha que tenha pelo menos uma letra maiuscula, um caracter especial e um caracter alfanumérico";
        loginViewModel.userAccount = response.userAccount;
        // Log.e(TAG, "presentLoginData() called with: response = [" + response + "]");
        //Do your decoration or filtering here
        output.get().displayLoginData(loginViewModel);
    }

    @Override
    public void presentLastUser(LoginResponse response) {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.lastUser = response.user;
        output.get().displayLastUser(loginViewModel);
    }
}
