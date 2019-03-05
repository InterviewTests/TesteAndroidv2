package com.example.androidtest.presentation.login

import com.example.androidtest.R
import com.example.androidtest.repository.UserAccount


interface LoginPresenterContract {
    fun loginSuccessfull()
    fun loginFailed(msg: String)
    fun invalidInputForm()
    fun requestInProgress()
    fun showLoggedUserData(account: UserAccount)
}

class LoginPresenter(private val activity: LoginActivityContract) : LoginPresenterContract {
    override fun requestInProgress() {
        activity.showLoading()
        activity.disableLoginButton()
    }

    override fun loginSuccessfull() {
        activity.hideLoading()
        activity.navigateToHomeActivity()
    }

    override fun loginFailed(msg: String) {
        activity.hideLoading()
        activity.showAlert(msg)
        activity.enableLoginButton()
    }

    override fun invalidInputForm() {
        activity.hideLoading()
        activity.showAlert(activity.getStringRes(R.string.login_alert_msg_invalid_input))
    }

    override fun showLoggedUserData(account: UserAccount) {
        account.credentials?.let {
            activity.showLoggedUser(it.user)
        }
    }

}
