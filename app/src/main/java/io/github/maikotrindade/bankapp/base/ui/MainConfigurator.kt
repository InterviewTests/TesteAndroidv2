package io.github.maikotrindade.bankapp.base.ui

import java.lang.ref.WeakReference

enum class MainConfigurator {

    INSTANCE;

    fun configure(activity: MainActivity) {

        //config interactor and callbacks
        val interactor = MainInteractor()
        interactor.presenter = MainPresenter()
        interactor.presenter.activityInput = WeakReference(activity)
        activity.interactor = activity.interactor ?: interactor
    }

}