package com.br.natanfelipe.bankapp.configurator

import com.br.natanfelipe.bankapp.interactor.HomeInteractor
import com.br.natanfelipe.bankapp.presenter.HomePresenter
import com.br.natanfelipe.bankapp.view.home.HomeActivity
import java.lang.ref.WeakReference

enum class HomeConfigurator {
    INSTANCE;

    fun configure(activity: HomeActivity) {

        /*val router = LoginRouter()
        router.activity = WeakReference<HomeActivity>(activity)*/

        val presenter = HomePresenter()
        presenter.output = WeakReference(activity)

        val interactor = HomeInteractor()
        interactor.output = presenter

        if (activity.output == null) {
            activity.output = interactor
        }
        /*if (activity.router == null) {
            activity.router = router
        }*/
    }
}
