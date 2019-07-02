package io.github.maikotrindade.bankapp.statement.domain

import io.github.maikotrindade.bankapp.statement.model.StatementsResponse
import java.lang.ref.WeakReference

interface LoginInteractorInput {
    fun getStatements(userId: Int)
}

class StatementsInteractor : LoginInteractorInput {

    lateinit var presenter: StatementsPresenter
    private var worker: StatementsWorker? = null

    init {
        worker = StatementsWorker(WeakReference(this))
    }

    override fun getStatements(userId: Int) {
        worker?.getStatements(userId)
    }

    fun onRequestStatementsSuccess(statementsResponse: StatementsResponse?) {
        when {
            statementsResponse?.statementList!!.isNotEmpty() -> {
                presenter.updateStatementsList(statementsResponse.statementList)
            }
            statementsResponse?.error?.code != null -> {
                presenter.showError(statementsResponse?.error?.message)
            }
            else -> onRequestStatementsError()
        }

    }

    fun onRequestStatementsError() {
        presenter.showError()
    }


}