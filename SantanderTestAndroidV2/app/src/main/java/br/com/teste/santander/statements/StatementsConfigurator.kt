package br.com.teste.santander.statements

import br.com.teste.santander.login.interactor.LoginInteractorImpl
import br.com.teste.santander.login.presenter.LoginPresenterImpl
import br.com.teste.santander.login.repository.LoginRepositoryImpl
import br.com.teste.santander.login.view.LoginActivity
import br.com.teste.santander.statements.interactor.StatementsInteractorImpl
import br.com.teste.santander.statements.presenter.StatementsPresenterImpl
import br.com.teste.santander.statements.repository.StatementsRepositoryImpl
import br.com.teste.santander.statements.view.StatementsActivity
import java.lang.ref.WeakReference

enum class StatementsConfigurator {

    INSTANCE;

    fun configure(activity: StatementsActivity) {

        val presenter = StatementsPresenterImpl()
        presenter.statementsView = WeakReference(activity)

        val repository = StatementsRepositoryImpl()

        val interactor = StatementsInteractorImpl()
        interactor.presenter = presenter
        interactor.repository = repository

        if (activity.interactor == null) {
            activity.interactor = interactor
        }
    }

}