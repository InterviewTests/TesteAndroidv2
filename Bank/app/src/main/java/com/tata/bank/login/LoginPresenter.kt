package com.tata.bank.login

import java.lang.ref.WeakReference

interface LoginPresenterInput {
    fun presentSuccess(loginResponse: LoginResponse?)
    fun presentStatusError(code: Int)
    fun presentError(error: Throwable)
}

class LoginPresenter: LoginPresenterInput {
    lateinit var output: WeakReference<LoginActivityInput>
    lateinit var router: LoginRouterInput

    override fun presentSuccess(loginResponse: LoginResponse?) {

        loginResponse?.let {
            it.error.message?.let { message ->
                output.get()?.displayError(message)
            }

            it.userAccount.let { userAccount ->
                router.goToMain(userAccount)
            }
        }
    }

    override fun presentStatusError(code: Int) {
        // TODO improve to filter message by code
        output.get()?.displayError("Http error: $code")
    }

    override fun presentError(error: Throwable) {
        // TODO improve error treatment
        output.get()?.displayError("An exception occurred")
    }
}