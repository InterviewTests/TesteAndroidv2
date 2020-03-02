package com.lucianogiardino.bankapp.ui.statement

import android.util.Log
import com.lucianogiardino.bankapp.domain.model.Statement
import com.lucianogiardino.bankapp.domain.usecase.FetchStatementUseCase

class StatementPresenter(
    private var view: StatementContract.View,
    private var fetchStatementUseCase: StatementContract.UseCase.FetchStatement
) : StatementContract.Presenter, StatementContract.Presenter.OnFetchStatementResponse {

    override fun fetchStatement() {
        fetchStatementUseCase.execute(this)
    }

    override fun onFetchStatementResponseSuccess(statementList: ArrayList<Statement>) {
        view.setupStatementList(statementList)
    }

    override fun onFetchStatementResponseFailed(msg: String) {
        view.showError(msg)
    }
}