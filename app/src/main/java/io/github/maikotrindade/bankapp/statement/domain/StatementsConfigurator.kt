package io.github.maikotrindade.bankapp.statement.domain

import io.github.maikotrindade.bankapp.base.ui.MainActivity
import io.github.maikotrindade.bankapp.statement.ui.StatementsListFragment
import java.lang.ref.WeakReference

enum class StatementsConfigurator {

    INSTANCE;

    fun configure(fragment: StatementsListFragment) {

        //config router
        val router = StatementsRouter()
        router.activity = WeakReference(fragment.activity as MainActivity)
        fragment.router = fragment.router ?: router

        //config interactor and callbacks
        val interactor = StatementsInteractor()
        interactor.presenter = StatementsPresenter()
        interactor.presenter.fragmentInput = WeakReference(fragment)
        fragment.interactor = fragment.interactor ?: interactor
    }

}