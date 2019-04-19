package br.com.rms.bankapp.ui.home

import br.com.rms.bankapp.base.mvp.BaseContract
import br.com.rms.bankapp.data.local.database.entity.Statement

interface HomeContract : BaseContract.View {
    interface View : BaseContract.View{
        fun onMoreStatementsReady(statements : List<Statement>)
        fun showErrorMessage(errorMessage: Int)
        fun showLoading()
        fun hideLoading()
        fun updateUserName(name: String)
        fun updateUserAccount(agency: String, account: String)
        fun updateUserBalance(balance: String)
    }

    interface Presenter : BaseContract.Presenter{
        fun loadMoreStatements()
        fun loadUserAccount()
    }
}
