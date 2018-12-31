package com.home.project.testeandroidv2.loginScreen;

import java.lang.ref.WeakReference;

interface LoginPresenterInput {
    void presentUserLoginData(LoginResponseActivity loginResponse);
}

public class LoginPresenter implements LoginPresenterInput {

    /*
    apresenta os dados do ultimo usuário logado na LoginActivity
     */

    public WeakReference<LoginActivity> output;

    @Override
    public void presentUserLoginData(LoginResponseActivity loginResponse) {
        if (loginResponse.userAccount != null) {
            output.get().displayUserLoginData(loginResponse);
        }
    }
}
