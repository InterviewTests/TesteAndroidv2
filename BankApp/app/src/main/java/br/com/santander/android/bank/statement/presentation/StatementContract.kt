package br.com.santander.android.bank.statement.presentation

import br.com.santander.android.bank.core.base.BasePresenter
import br.com.santander.android.bank.core.base.BaseView
import br.com.santander.android.bank.statement.domain.model.Statements

interface StatementContract {

    interface View : BaseView<Presenter> {
        fun showLoading()
        fun hideLoading()
        fun showStatements(statements: Statements)
        fun showTryAgainMessage()
    }

    interface Presenter : BasePresenter {
        fun requestStatements(userId: Int)
    }
}