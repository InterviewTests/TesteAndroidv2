package br.com.learncleanarchitecture.login.presentation

import br.com.learncleanarchitecture.login.domain.LoginInteractor.Companion.EMPTY_TYPE
import br.com.learncleanarchitecture.login.domain.LoginInteractor.Companion.PASSWORD_TYPE
import br.com.learncleanarchitecture.login.domain.LoginInteractor.Companion.USER_NAME_TYPE
import java.lang.ref.WeakReference

interface LoginPresenterInput {
    fun presentLoginMetaData(response: LoginResponse)
    fun showError(errorType: String, msg: String)
    fun callLogin()
    fun presentLoginInUserFields(loginResponse: LoginResponse)
}

class LoginPresenter : LoginPresenterInput {

    var output: WeakReference<LoginFragmentInput>? = null

    override fun presentLoginInUserFields(loginResponse: LoginResponse) {
        output?.get()?.putUsernameAndPasswordInFields(loginResponse)
    }

    override fun callLogin() {
        output?.get()?.callLogin()
    }

    override fun showError(errorType: String, msg: String) {

        if (errorType == EMPTY_TYPE) {
            output?.get()?.showErrorResponse(msg)
        }

        if (errorType == USER_NAME_TYPE) {
            output?.get()?.usernameError(msg)
        }

        if (errorType == PASSWORD_TYPE) {
            output?.get()?.passwordError(msg)
        }
    }

    override fun presentLoginMetaData(response: LoginResponse) {
        if (response.error == null || response.error?.message == "") {
            output?.get()?.responseServiceLogin(response.login!!)
        } else {
            output?.get()?.showErrorResponse(response.error?.message!!)
        }
    }
}
