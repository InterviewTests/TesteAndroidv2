package com.zuptest.santander.statement

import com.zuptest.santander.domain.business.model.Account
import com.zuptest.santander.domain.business.model.Statement

object StatementsContract {

    interface View {
        fun displayHolderName(name: String)
        fun displayAccountInfo(agency: String, account: String)
        fun displayBalance(balance: String)
        fun displayStatements(statements: List<Statement>)
    }

    interface Presenter {
        fun init(account: Account)
    }
}