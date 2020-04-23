package com.tata.bank.login

import java.lang.ref.WeakReference

enum class LoginConfigurator {
    INSTANCE;

    fun configure(activity: LoginActivity) {

        val router = LoginRouter()
        router.activity = WeakReference(activity)

        val presenter = LoginPresenter()
        presenter.output = WeakReference(activity)

        val interactor = LoginInteractor()
        interactor.output = presenter

        if (activity.output == null){
            activity.output = interactor;
        }

        if (activity.router == null){
            activity.router = router;
        }
    }
}