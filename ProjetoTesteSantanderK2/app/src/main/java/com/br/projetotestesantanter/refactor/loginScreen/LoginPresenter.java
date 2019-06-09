package com.br.projetotestesantanter.refactor.loginScreen;


import java.lang.ref.WeakReference;

interface LoginPresenterInput {
    void presentLoginMetaData(LoginModel.Login response);
    void displayMessageErro(String message);
    void visibleProgressBar();
    void hideProgressBar();
}

public class LoginPresenter implements LoginPresenterInput {

    public WeakReference<LoginActivityInput> output;

    @Override
    public void presentLoginMetaData(LoginModel.Login response) {
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
