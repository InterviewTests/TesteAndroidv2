package io.github.maikotrindade.bankapp.base.ui

interface MainInteractorInput {
    fun handleUserSession()
}

class MainInteractor : MainInteractorInput {

    lateinit var presenter: MainPresenter

    override fun handleUserSession() {
        presenter.handleUserSession()
    }

}