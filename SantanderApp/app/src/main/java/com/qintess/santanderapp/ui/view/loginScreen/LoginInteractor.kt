package com.qintess.santanderapp.ui.view.loginScreen

interface LoginInteractorInput {
    fun login(request: LoginRequest)
    fun checkLastUser()
    fun getLastUser(): String?
    fun isCredentialsValid(credentials: LoginRequest): Boolean
}

open class LoginInteractor: LoginInteractorInput {
    var output: LoginPresenterInput? = null

    override fun login(request: LoginRequest) {
        if (isCredentialsValid(request)) {
            // do login
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

    override fun isCredentialsValid(credentials: LoginRequest): Boolean {
        return false
    }

}