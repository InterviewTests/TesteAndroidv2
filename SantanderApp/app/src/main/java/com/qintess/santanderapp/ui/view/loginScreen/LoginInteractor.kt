package com.qintess.santanderapp.ui.view.loginScreen

import com.qintess.santanderapp.helper.Validator

interface LoginInteractorInput {
    fun login(request: LoginRequest)
    fun checkLastUser()
    fun getLastUser(): String?
    fun getCredentialsError(credentials: LoginRequest): String?
}

open class LoginInteractor: LoginInteractorInput {
    var output: LoginPresenterInput? = null

    override fun login(request: LoginRequest) {
        val errorMsg = getCredentialsError(request)
        if (errorMsg == null) {
            // do login
        } else {
            output?.presentLoginErrorMessage(errorMsg)
        }
    }

    override fun checkLastUser() {
        val lastUser = getLastUser()
        if (lastUser != null) {
            output?.presentLastUser(lastUser)
        }
    }

    override fun getLastUser(): String? {
        return null
    }

    override fun getCredentialsError(credentials: LoginRequest): String? {
        if (!credentials.username?.valid!!) {
            return Validator.USER_ERROR
        } else if (!credentials.password?.valid!!) {
            return Validator.PASS_ERROR
        }
        return null
    }

}