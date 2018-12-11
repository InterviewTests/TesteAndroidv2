package com.accenture.primo.bankapp.ui.login

enum class LoginConfigurator {
    INSTANCE;

    fun configure(activity: LoginActivity) {
        val router = LoginRouter(activity)
        val presenter = LoginPresenter(activity)
        val interactor = LoginInteractor(presenter)

        if (activity.interactor == null)
            activity.interactor = interactor

        if (activity.router == null)
            activity.router = router
    }
}
