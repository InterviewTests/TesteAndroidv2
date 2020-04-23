package com.tata.bank.main

import java.lang.ref.WeakReference

enum class MainConfigurator {

    INSTANCE;

    fun configure(activity: MainActivity) {
        val router = MainRouter()
        router.activity = WeakReference(activity)

        val presenter = MainPresenter()
        presenter.output = WeakReference(activity)
        presenter.router = router

        val interactor = MainInteractor()
        interactor.output = presenter

        activity.output = interactor
    }
}