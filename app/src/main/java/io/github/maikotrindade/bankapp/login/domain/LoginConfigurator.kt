package io.github.maikotrindade.bankapp.login.domain

import io.github.maikotrindade.bankapp.base.ui.MainActivity
import io.github.maikotrindade.bankapp.login.ui.LoginFragment
import java.lang.ref.WeakReference

enum class LoginConfigurator {

    INSTANCE;

    fun configure(fragment: LoginFragment) {

        //config router
        val router = LoginRouter()
        router.activity = WeakReference(fragment.activity as MainActivity)
        fragment.router = fragment.router ?: router

        //config interactor and callbacks
        val interactor = LoginInteractor()
        interactor.presenter = LoginPresenter()
        interactor.presenter.fragmentInput = WeakReference(fragment)
        fragment.interactor = fragment.interactor ?: interactor

    }

}