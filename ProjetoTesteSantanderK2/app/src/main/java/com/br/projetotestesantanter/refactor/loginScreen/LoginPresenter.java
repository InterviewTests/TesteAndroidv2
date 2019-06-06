package com.br.projetotestesantanter.refactor.loginScreen;


import java.lang.ref.WeakReference;

interface LoginPresenterInput {
    void presentLoginMetaData(LoginModel.LoginResponse response);
}

public class LoginPresenter implements LoginPresenterInput {

    public static String TAG = LoginPresenter.class.getSimpleName();

    public WeakReference<LoginActivityInput> output;

    @Override
    public void presentLoginMetaData(LoginModel.LoginResponse response) {



        output.get().displayHomeMetaData(response);

    }
}
