package com.br.natanfelipe.bankapp.configurator

import com.br.natanfelipe.bankapp.interactor.HomeInteractor
import com.br.natanfelipe.bankapp.interactor.LoginInteractor
import com.br.natanfelipe.bankapp.presenter.HomePresenter
import com.br.natanfelipe.bankapp.presenter.LoginPresenter
import com.br.natanfelipe.bankapp.router.LoginRouter
import com.br.natanfelipe.bankapp.view.home.HomeActivity
import com.br.natanfelipe.bankapp.view.login.LoginActivity
import java.lang.ref.WeakReference

enum class LoginConfigurator {
    INSTANCE;

    fun configure(activity: LoginActivity) {

        /*val router = LoginRouter()
        router.activity = WeakReference<LoginActivity>(activity)*/

        val presenter = LoginPresenter()
        presenter.output = WeakReference(activity)

        val interactor = LoginInteractor()
        interactor.output = presenter

        if (activity.output == null) {
            activity.output = interactor
        }
        /*if (activity.router == null) {
            activity.router = router
        }*/
    }
}
