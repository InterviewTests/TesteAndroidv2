package com.qintess.santanderapp.ui.view.statementsScreen

import java.lang.ref.WeakReference

enum class StatementsConfigurator {
    INSTANCE;

    fun configure(activity: StatementsActivity) {
        val presenter = StatementsPresenter()
        presenter.output = WeakReference(activity)

        val interactor = StatementsInteractor()
        interactor.output = presenter

        if (activity.output == null) {
            activity.output = interactor
        }
    }
}