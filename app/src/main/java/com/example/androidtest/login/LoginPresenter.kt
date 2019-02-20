package com.example.androidtest.login

import com.example.androidtest.R


interface LoginPresenterContract {
    fun loginSuccessfull()
    fun loginFailed(msg: String)
    fun invalidInputForm()
}

class LoginPresenter(
    private val activity: LoginActivityContract
) : LoginPresenterContract {

    override fun loginSuccessfull() {
        activity.disableLoading()
        activity.navigateToHomeActivity()
    }

    override fun loginFailed(msg: String) {
        activity.disableLoading()
        activity.showAlert(msg)
    }

    override fun invalidInputForm() {
        activity.disableLoading()
        activity.showAlert(activity.getStringRes(R.string.login_alert_msg_invalid_input))
    }


}
