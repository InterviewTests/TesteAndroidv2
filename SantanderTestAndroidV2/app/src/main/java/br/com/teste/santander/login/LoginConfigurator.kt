package br.com.teste.santander.login

import br.com.teste.santander.login.interactor.LoginInteractorImpl
import br.com.teste.santander.login.presenter.LoginPresenterImpl
import br.com.teste.santander.login.repository.LoginRepositoryImpl
import br.com.teste.santander.login.view.LoginActivity
import java.lang.ref.WeakReference


enum class LoginConfigurator {

    INSTANCE;

    fun configure(activity: LoginActivity) {

        val presenter = LoginPresenterImpl()
        presenter.loginView = WeakReference(activity)

        val repository = LoginRepositoryImpl()

        val interactor = LoginInteractorImpl()
        interactor.presenter = presenter
        interactor.repository = repository

        if (activity.interactor == null) {
            activity.interactor = interactor
        }
    }

}