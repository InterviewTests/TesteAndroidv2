package com.tata.bank.login

import java.lang.ref.WeakReference

interface LoginPresenterInput {
    fun presentSuccess(loginResponse: LoginResponse?)
    fun presentStatusError(code: Int)
    fun presentError(error: Throwable)
    fun fillLoginFields(user: String, password: String)
}

class LoginPresenter : LoginPresenterInput {
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
        output.get()?.displayError("An error has occurred($code)")
    }

    override fun presentError(error: Throwable) {
        val message = error.message ?: "An exception has occurred"
        output.get()?.displayError(message)
    }

    override fun fillLoginFields(user: String, password: String) {
        output.get()?.fillLoginFields(user, password)
    }
}