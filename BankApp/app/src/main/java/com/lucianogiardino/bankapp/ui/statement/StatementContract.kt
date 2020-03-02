package com.lucianogiardino.bankapp.ui.statement

import com.lucianogiardino.bankapp.domain.model.Statement

interface StatementContract {
    interface View {
        fun showError(msg: String)
        fun setupUserData()
        fun setupStatementList(statementList: ArrayList<Statement>)
        fun goToLogin()
    }

    interface Presenter {
        fun fetchStatement()

        interface OnFetchStatementResponse{
            fun onFetchStatementResponseSuccess(statementList: ArrayList<Statement>)
            fun onFetchStatementResponseFailed(msg: String)
        }

        fun logout()
    }

    interface UseCase{
        interface FetchStatement{
            fun execute(listener: StatementContract.Presenter.OnFetchStatementResponse)
        }
        interface Logout{
            fun execute()
        }

    }
}