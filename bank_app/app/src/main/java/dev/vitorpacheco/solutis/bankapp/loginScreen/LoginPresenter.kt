package dev.vitorpacheco.solutis.bankapp.loginScreen

import java.lang.ref.WeakReference

interface LoginPresenterInput {
    fun presentLoginData(response: LoginResponse?)
}

class LoginPresenter : LoginPresenterInput {

    var output: WeakReference<LoginActivityInput>? = null

    override fun presentLoginData(response: LoginResponse?) {
        response?.let {
            output?.get()?.displayLoginData(LoginViewModel(
                response.user,
                response.userAccount,
                it.error?.message,
                it.invalidUser,
                it.invalidPassword
            ))
        }
    }

    companion object {
        var TAG = LoginPresenter::class.java.simpleName
    }

}
