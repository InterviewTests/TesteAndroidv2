package com.qintess.santanderapp.ui.view.loginScreen

import java.lang.ref.WeakReference

interface LoginPresenterInput {
    fun presentLastUser(username: String)
    fun presentLoginErrorMessage(msg: String)
}

class LoginPresenter: LoginPresenterInput {
    var output: WeakReference<LoginActivityInput>? = null

    override fun presentLastUser(username: String) {
        output?.get()?.displayLastUser(username)
    }

    override fun presentLoginErrorMessage(msg: String) {
        output?.get()?.showAlert(msg)
    }
}