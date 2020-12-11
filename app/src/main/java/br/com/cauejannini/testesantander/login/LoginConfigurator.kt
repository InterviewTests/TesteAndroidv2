package br.com.cauejannini.testesantander.login

import java.lang.ref.WeakReference

object LoginConfigurator {

    fun configure(loginActivity: LoginActivity) {

        val presenter = LoginPresenter()
        presenter.output = WeakReference(loginActivity)

        val interactor = LoginInteractor()
        interactor.output = presenter

        loginActivity.output = interactor

    }

}