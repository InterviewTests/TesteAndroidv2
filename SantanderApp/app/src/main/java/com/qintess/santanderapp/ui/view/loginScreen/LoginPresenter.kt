package com.qintess.santanderapp.ui.view.loginScreen

interface LoginPresenterInput {
    fun presentLastUser(username: String)
}

class LoginPresenter: LoginPresenterInput {
    var output: LoginActivityInput? = null

    override fun presentLastUser(username: String) {
        TODO("Not yet implemented")
    }
}