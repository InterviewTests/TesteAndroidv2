package com.accenture.primo.bankapp.ui.main

enum class MainConfigurator {
    INSTANCE;

    fun configure(activity: MainActivity) {
        val router = MainRouter(activity)
        val presenter = MainPresenter(activity)
        val interactor = MainInteractor(presenter)

        if (activity.interactor == null)
            activity.interactor = interactor

        if (activity.router == null)
            activity.router = router
    }
}
