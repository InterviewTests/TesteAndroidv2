package com.android.bankapp.login;

import com.android.bankapp.login.interactor.LoginInteractor;
import com.android.bankapp.login.presenter.LoginPresenter;
import com.android.bankapp.login.view.LoginActivity;
import com.android.bankapp.login.router.LoginRouter;

import java.lang.ref.WeakReference;

public enum LoginConfigurator {

    INSTANCE;

    public void configure(LoginActivity activity){
        LoginRouter router = new LoginRouter();
        router.activity = new WeakReference<>(activity);

        LoginPresenter presenter = new LoginPresenter();
        presenter.output = new WeakReference<>(activity);

        LoginInteractor interactor = new LoginInteractor();
        interactor.output = presenter;

        if (activity.output == null){
            activity.output = interactor;
        }
        if (activity.router == null){
            activity.router = router;
        }

    }
}
