package com.lucianogiardino.bankapp.ui.statement

import android.util.Log
import com.lucianogiardino.bankapp.domain.model.Statement
import com.lucianogiardino.bankapp.domain.usecase.FetchStatementUseCase
import com.lucianogiardino.bankapp.domain.usecase.LogoutUseCase

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