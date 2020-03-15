package dev.vitorpacheco.solutis.bankapp.loginScreen

import java.lang.ref.WeakReference

interface LoginPresenterInput {
    fun presentLoginData(response: LoginResponse)
}

class LoginPresenter : LoginPresenterInput {

    var output: WeakReference<LoginActivityInput>? = null

    override fun presentLoginData(response: LoginResponse) {
        output?.get()?.displayLoginData(LoginViewModel(null, null, response.error?.message))
    }

    companion object {
        var TAG = LoginPresenter::class.java.simpleName
    }

}
