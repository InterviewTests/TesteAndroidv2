package com.accenture.primo.bankapp.ui.login

import android.content.Context
import com.accenture.primo.bankapp.model.User
import com.accenture.primo.bankapp.ui.login.contracts.ILoginActivity
import com.accenture.primo.bankapp.ui.login.contracts.ILoginPresenter

class LoginPresenter(private val outputActivity: ILoginActivity) : ILoginPresenter {
    override fun getContext(): Context = outputActivity.getContext()

    override fun showLoading() {
        outputActivity.showLoading()
    }

    override fun hideLoading() {
        outputActivity.hideLoading()
    }

    override fun onError(error: String) {
        outputActivity.onError(error)
    }

    override fun onSuccess (user: User) {
        outputActivity.onSuccess(user)
    }

    override fun showPreferences(pref: LoginModel.LoginViewModel) {
        outputActivity.showPreferences(pref)
    }

}
