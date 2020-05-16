package com.lucianogiardino.bankapp.presentation.statement

import com.lucianogiardino.bankapp.domain.model.Statement

class StatementPresenter(
    private var view: StatementContract.View,
    private var fetchStatementUseCase: StatementContract.UseCase.FetchStatement,
    private var logoutUseCase: StatementContract.UseCase.Logout
) : StatementContract.Presenter, StatementContract.Presenter.OnFetchStatementResponse {

    override fun fetchStatement() {
        fetchStatementUseCase.execute(this)
    }

    override fun logout() {
        logoutUseCase.execute()
        view.goToLogin()
    }

    override fun onFetchStatementResponseSuccess(statementList: ArrayList<Statement>) {
        view.setupStatementList(statementList)
    }

    override fun onFetchStatementResponseFailed(msg: String) {
        view.showError(msg)
    }
}