package com.bilulo.androidtest04.ui.login.configurator;

import com.bilulo.androidtest04.ui.login.contract.LoginContract;
import com.bilulo.androidtest04.ui.login.interactor.LoginInteractor;
import com.bilulo.androidtest04.ui.login.presenter.LoginPresenter;
import com.bilulo.androidtest04.ui.login.router.LoginRouter;
import com.bilulo.androidtest04.ui.login.view.LoginActivity;

import java.lang.ref.WeakReference;

public class LoginConfigurator {
    public static void configure(LoginActivity activity) {
        LoginRouter router = new LoginRouter();
        router.activity = new WeakReference<>(activity);

        LoginPresenter presenter = new LoginPresenter();
        presenter.activity = new WeakReference<LoginContract.ActivityContract>(activity);

        LoginInteractor interactor = new LoginInteractor();
        interactor.presenter = presenter;

        if (activity.interactor == null) {
            activity.interactor = interactor;
        }
        if (activity.router == null) {
            activity.router = router;
        }
    }
}
