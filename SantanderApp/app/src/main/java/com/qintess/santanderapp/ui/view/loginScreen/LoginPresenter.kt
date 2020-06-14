package com.qintess.santanderapp.ui.view.loginScreen

import com.qintess.santanderapp.model.UserModel
import java.lang.ref.WeakReference

interface LoginPresenterInput {
    fun presentLastUser(username: String)
    fun presentErrorMessage(title: String, msg: String)
    fun presentStatementScreen(user: UserModel)
}

class LoginPresenter: LoginPresenterInput {
    var output: WeakReference<LoginActivityInput>? = null

    override fun presentLastUser(username: String) {
        output?.get()?.displayLastUser(username)
    }

    override fun presentErrorMessage(title: String, msg: String) {
        output?.get()?.showAlert(title, msg)
    }

    override fun presentStatementScreen(user: UserModel) {
        output?.get()?.goToStatements(user)
    }
}