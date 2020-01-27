package com.msbm.bank.loginScreen;

import java.lang.ref.WeakReference;

public enum LoginConfigurator {
    INSTANCE;
    public void configure(LoginActivity loginActivity) {
        LoginPresenter loginPresenter = new LoginPresenter();
        loginPresenter.loginActivity = new WeakReference<>(loginActivity);

        LoginInteractor loginInteractor = new LoginInteractor();
        loginInteractor.loginPresenterInput = loginPresenter;

        if (loginActivity.loginInteractor == null) {
            loginActivity.loginInteractor = loginInteractor;
        }
    }
}
