package com.br.rafael.TesteAndroidv2.loginScreen;

import com.br.rafael.TesteAndroidv2.data.model.LoginResponse;

import java.lang.ref.WeakReference;

interface LoginPresenterInput {
    void presentLoginMetaData(LoginResponse response);
    void displayMessageErro(String message);
    void visibleProgressBar();
    void hideProgressBar();
}

public class LoginPresenter  implements LoginPresenterInput {

    public WeakReference<LoginActivityInput> output;

    @Override
    public void presentLoginMetaData(LoginResponse response) {
        output.get().displayHomeMetaData(response);
    }

    @Override
    public void displayMessageErro(String message) {
        output.get().displayMensagem(message);
    }

    @Override
    public void visibleProgressBar() {
        output.get().showProgressBar();
    }

    @Override
    public void hideProgressBar() {
        output.get().hideProgressBar();
    }
}
