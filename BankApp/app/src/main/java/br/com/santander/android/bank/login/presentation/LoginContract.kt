package br.com.santander.android.bank.login.presentation

import br.com.santander.android.bank.core.base.BasePresenter
import br.com.santander.android.bank.core.base.BaseView
import br.com.santander.android.bank.login.domain.model.Account
import br.com.santander.android.bank.login.domain.model.UserAccount

interface LoginContract {

    interface View : BaseView<Presenter> {
        fun showLoading()
        fun hideLoading()
        fun showEmptyUserError()
        fun showEmptyPasswordError()
        fun showInvalidPasswordError()
        fun showOfflineMessage()
        fun showTryAgainMessage()
        fun onLoginSuccess(userAccount: UserAccount)
    }

    interface Presenter : BasePresenter {
        fun requestLogin(login: String, password: String)
        fun saveSession(userAccount: UserAccount)
        fun getSavedSession(): Account?
    }
}