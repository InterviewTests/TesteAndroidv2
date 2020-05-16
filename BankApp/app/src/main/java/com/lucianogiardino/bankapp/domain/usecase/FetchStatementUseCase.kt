package com.lucianogiardino.bankapp.domain.usecase

import com.lucianogiardino.bankapp.domain.model.Statement
import com.lucianogiardino.bankapp.presentation.statement.StatementContract


class FetchStatementUseCase(
    private val statementRepository: StatementContract.Repository) :
    StatementContract.UseCase.FetchStatement, StatementContract.UseCase.OnFetchStatementResponse {

    private lateinit var presenter: StatementContract.Presenter.OnFetchStatementResponse

    override fun execute(listener: StatementContract.Presenter.OnFetchStatementResponse){
        presenter = listener
        statementRepository.fetchStatement(this)
    }

    override fun onFetchStatementResponseSuccess(statementList: ArrayList<Statement>) {
        presenter.onFetchStatementResponseSuccess(statementList)
    }

    override fun onFetchStatementResponseFailed(error: String) {
        presenter.onFetchStatementResponseFailed(error)
    }
}