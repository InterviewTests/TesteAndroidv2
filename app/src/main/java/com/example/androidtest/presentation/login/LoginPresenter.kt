package com.example.androidtest.presentation.login

import com.example.androidtest.R


interface LoginPresenterContract {
    fun loginSuccessfull()
    fun loginFailed(msg: String)
    fun invalidInputForm()
    fun requestInProgress()
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


}
