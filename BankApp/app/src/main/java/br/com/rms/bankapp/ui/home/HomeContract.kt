package br.com.rms.bankapp.ui.home

import br.com.rms.bankapp.base.mvp.BaseContract
import br.com.rms.bankapp.data.local.database.entity.Statement
import br.com.rms.bankapp.ui.login.LoginContract

interface HomeContract : BaseContract.View {
    interface View : BaseContract.View{
        fun onMoreStatementsReady(statements : List<Statement>)
        fun onLoadStatementFailed()
        fun showLoading()
        fun hideLoading()

    }

    interface Presenter : BaseContract.Presenter<View>{
        fun loadStatements()
        fun loadMoreStatemens()

    }
}
