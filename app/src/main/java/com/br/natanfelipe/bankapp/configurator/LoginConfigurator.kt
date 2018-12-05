package com.br.natanfelipe.bankapp.configurator

import com.br.natanfelipe.bankapp.interactor.LoginInteractor
import com.br.natanfelipe.bankapp.presenter.LoginPresenter
import com.br.natanfelipe.bankapp.view.login.LoginActivity
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
