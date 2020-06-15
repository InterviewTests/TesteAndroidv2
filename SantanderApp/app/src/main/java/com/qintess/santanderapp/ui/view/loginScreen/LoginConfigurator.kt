package com.qintess.santanderapp.ui.view.loginScreen

import java.lang.ref.WeakReference

enum class LoginConfigurator {
    INSTANCE;

    fun configure(activity: LoginActivity) {
        val presenter = LoginPresenter()
        presenter.output = WeakReference(activity)

        val interactor = LoginInteractor()
        interactor.output = presenter

        if (activity.output == null) {
            activity.output = interactor
        }
    }
}