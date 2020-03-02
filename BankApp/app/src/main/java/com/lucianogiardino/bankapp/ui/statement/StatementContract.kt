package com.lucianogiardino.bankapp.ui.statement

import com.lucianogiardino.bankapp.domain.model.Statement

interface StatementContract {
    interface View {
        fun showError(msg: String)
        fun setupUserData()
    }

    interface Presenter {
        fun fetchStatement()

        interface OnFetchStatementResponse{
            fun onFetchStatementResponseSuccess(statementList: ArrayList<Statement>)
            fun onFetchStatementResponseFailed(msg: String)
        }
    }
}