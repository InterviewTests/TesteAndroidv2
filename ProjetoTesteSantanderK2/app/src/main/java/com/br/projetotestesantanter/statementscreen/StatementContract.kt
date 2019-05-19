package com.br.projetotestesantanter.statementscreen

import com.br.projetotestesantanter.BasePresenter
import com.br.projetotestesantanter.BaseView
import com.br.projetotestesantanter.api.model.LoginResponse
import com.br.projetotestesantanter.api.model.StatementListResponse

interface StatementContract {

    interface View : BaseView {

        fun listStatement(statements : StatementListResponse)

        fun dataHeader(loginResponse: LoginResponse)

    }

    interface Presenter : BasePresenter {

        fun attachView(view: View)

        fun resultLogin(loginResponse : LoginResponse)
    }
}