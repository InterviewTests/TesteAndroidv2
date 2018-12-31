package com.home.project.testeandroidv2.loginScreen;

import java.lang.ref.WeakReference;

public enum LoginConfigurator {
    INSTANCE;

    public void configure(LoginActivity loginActivity){

        LoginRouter loginRouter = new LoginRouter();
        loginRouter.activity = new WeakReference<>(loginActivity);


        LoginPresenter loginPresenter = new LoginPresenter();
        loginPresenter.output = new WeakReference<>(loginActivity);

        LoginInteractor loginInteractor = new LoginInteractor();
        loginInteractor.output = loginPresenter;

        if(loginActivity.loginRouter == null){
            loginActivity.loginRouter = loginRouter;
        }

        if(loginActivity.output == null){
            loginActivity.output = loginInteractor;
        }
    }

}
