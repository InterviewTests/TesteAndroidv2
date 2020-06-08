package br.com.cauejannini.testesantander.login

import java.lang.ref.WeakReference

interface LoginPresenterInput {
    fun onLoginResponse(loginResponse: LoginResponseModel)
    fun onLoginFailed(message: String)
}

class LoginPresenter: LoginPresenterInput {

    var output: WeakReference<LoginActivityInput>? = null

    override fun onLoginResponse(loginResponse: LoginResponseModel) {
        val userAccount = loginResponse.userAccount
        if (userAccount != null) {
            output?.get()?.onLoggedIn(userAccount)
        } else {
            output?.get()?.onLoginError(loginResponse.error.toString())
        }
    }

    override fun onLoginFailed(message: String) {
        output?.get()?.onLoginError(message)
    }

}