package com.accenture.project.apptesteandroid.login;

import java.lang.ref.WeakReference;

/**
 Classe que configura instâncias necessárias para o módulo de Login
 */

public enum  LoginConfigurator {

    GET_INSTANCE;

    public void configure(LoginActivity loginActivity) {

        LoginRouter loginRouter = new LoginRouter();
        loginRouter.loginActivity = new WeakReference<>(loginActivity);

        LoginPresenter loginPresenter = new LoginPresenter();
        loginPresenter.iLoginActivity = new WeakReference<ILoginActivity>(loginActivity);


        LoginInteractor loginInteractor = new LoginInteractor();
        loginInteractor.presenter = loginPresenter;
        loginInteractor.context = loginActivity.getApplicationContext();
        loginInteractor.router = loginRouter;


        if (loginActivity.loginInteractor == null) {

            loginActivity.loginInteractor = loginInteractor;
        }


        if (loginActivity.router == null) {

            loginActivity.router = loginRouter;
        }

    }
}
