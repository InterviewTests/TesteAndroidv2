package com.santander.app.feature.statement

import com.santander.app.core.ui.base.BasePresenter
import com.santander.app.core.ui.base.BaseView
import com.santander.domain.entity.business.Statement
import com.santander.domain.entity.business.UserAccount

object StatementContract {

    interface View : BaseView {
        fun displayUserAccount(userAccount: UserAccount)
        fun displayStatements(statements: List<Statement>)
    }

    interface Presenter : BasePresenter<View> {
        fun fetchStatements()
        fun logout()
    }
}