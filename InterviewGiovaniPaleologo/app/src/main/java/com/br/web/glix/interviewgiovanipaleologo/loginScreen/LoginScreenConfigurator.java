package com.br.web.glix.interviewgiovanipaleologo.loginScreen;

import java.lang.ref.WeakReference;

public enum LoginScreenConfigurator {
    INSTANCE;

    public void configure(LoginScreenActivity loginScreenActivity) {
        LoginScreenPresenter loginScreenPresenter = new LoginScreenPresenter();
        loginScreenPresenter.output = new WeakReference<LoginScreenActivityInput>(loginScreenActivity);

        LoginScreenInteractor loginScreenInteractor = new LoginScreenInteractor();
        loginScreenInteractor.loginScreenPresenterInput = loginScreenPresenter;

        if (loginScreenActivity.loginScreenInteractor == null) {
            loginScreenActivity.loginScreenInteractor = loginScreenInteractor;
        }
    }
}
