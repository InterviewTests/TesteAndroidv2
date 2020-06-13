package com.qintess.santanderapp.ui.view.loginScreen

import com.qintess.santanderapp.helper.Validator
import com.qintess.santanderapp.model.CredentialsModel
import com.qintess.santanderapp.service.UserService

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
            val handler = android.os.Handler()
            val credentials = CredentialsModel(request.username?.text.toString(), request.password?.text.toString())
            val userService = UserService()
            userService.login(credentials,
                onSuccess = {
                    //Proxima tela
                },
                onFailure = {
                    handler.post {
                        output?.presentErrorMessage("", "")
                    }
                }
            )
        } else {
            output?.presentErrorMessage(Validator.CREDENTIALS_TITLE_ERROR, errorMsg)
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