package com.lucianogiardino.bankapp.ui.statement

import com.lucianogiardino.bankapp.domain.model.Statement
import com.lucianogiardino.bankapp.domain.usecase.FetchStatementUseCase

class StatementPresenter(
    private var view: StatementContract.View,
    private var fetchStatementUseCase: FetchStatementUseCase
) : StatementContract.Presenter, StatementContract.Presenter.OnFetchStatementResponse {

    override fun fetchStatement() {
        fetchStatementUseCase.execute(this)
    }

    override fun onFetchStatementResponseSuccess(statementList: ArrayList<Statement>) {
        statementList.get(0).title
    }

    override fun onFetchStatementResponseFailed(msg: String) {
        view.showError(msg)
    }
}