package br.com.cauejannini.testesantander.statements

import java.lang.ref.WeakReference

object StatementsConfigurator {

    fun configure(statementsActivity: StatementsActivity) {

        val presenter = StatementsPresenter()
        presenter.output = WeakReference(statementsActivity)

        val interactor = StatementsInteractor()
        interactor.output = presenter

        statementsActivity.output = interactor

    }

}