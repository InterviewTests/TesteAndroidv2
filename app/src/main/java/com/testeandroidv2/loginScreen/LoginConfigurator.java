package com.testeandroidv2.loginScreen;

import java.lang.ref.WeakReference;


public enum LoginConfigurator {
    INSTANCE;

    public void configure(LoginActivity activity) {

        LoginRouter router = new LoginRouter();
        router.activity = new WeakReference<>(activity);

        LoginPresenter presenter = new LoginPresenter();
        presenter.output = new WeakReference<LoginActivityInput>(activity);

        LoginInteractor interactor = new LoginInteractor();
        interactor.output = presenter;

        if (activity.output == null) {
            activity.output = interactor;
        }
        if (activity.router == null) {
            activity.router = router;
        }
    }
}
