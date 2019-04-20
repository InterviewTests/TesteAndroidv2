package br.com.rms.bankapp.ui.home

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import br.com.rms.bankapp.base.mvp.BaseContract
import br.com.rms.bankapp.data.local.database.entity.Account
import br.com.rms.bankapp.data.local.database.entity.Statement

interface HomeContract : BaseContract.View {
    interface View : BaseContract.View, LifecycleOwner {
        override fun getLifecycle(): Lifecycle
        fun onMoreStatementsReady(statements: List<Statement>)
        fun showErrorMessage(errorMessage: Int)
        fun showLoading()
        fun hideLoading()
        fun accountIsReady(userAccountInfo: Account)
    }

    interface Presenter : BaseContract.Presenter {
        fun loadMoreStatements()
        fun loadUserAccount()
    }
}
