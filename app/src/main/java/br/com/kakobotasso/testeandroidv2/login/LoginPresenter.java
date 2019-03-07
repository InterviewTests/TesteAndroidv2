package br.com.kakobotasso.testeandroidv2.login;

import java.lang.ref.WeakReference;

interface LoginPresenterInput {
    void presentLoginData(LoginResponse response);
}

public class LoginPresenter implements LoginPresenterInput {
    public WeakReference<LoginActivityInput> output;

    @Override
    public void presentLoginData(LoginResponse response) {
        if(response.hasErrors()) {
            output.get().displayLoginError(response.getErrorMessage());
        } else {
            output.get().displayCurrency(response);
        }
    }
}
