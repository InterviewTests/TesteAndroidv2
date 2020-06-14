package com.qintess.santanderapp.ui.view.loginScreen

import android.util.Log
import com.qintess.santanderapp.helper.Validator
import com.qintess.santanderapp.model.CredentialsModel
import com.qintess.santanderapp.service.UserService

interface LoginInteractorInput {
    fun login(request: LoginRequest)
    fun auth(credentials: CredentialsModel)
    fun checkLastUser(username: String?)
    fun getCredentialsError(credentials: LoginRequest): String?
}

open class LoginInteractor: LoginInteractorInput {
    var output: LoginPresenterInput? = null
    open var userService = UserService()
    private val handler = android.os.Handler()

    val TAG = this.javaClass.name

    override fun login(request: LoginRequest) {
        val errorMsg = getCredentialsError(request)
        if (errorMsg == null) {
            val credentials = CredentialsModel(request.username?.text.toString(), request.password?.text.toString())
            auth(credentials)
        } else {
            output?.presentErrorMessage(Validator.CREDENTIALS_TITLE_ERROR, errorMsg)
        }
    }

    override fun auth(credentials: CredentialsModel) {
        userService.login(credentials,
            onSuccess = {
                output?.presentStatementScreen(it)
            },
            onFailure = {
                Log.e(TAG, it.message ?: "Erro ao realizar login")
                output?.presentErrorMessage(Validator.LOGIN_TITLE_ERROR, "Tente novamente mais tarde")
            }
        )
    }

    override fun checkLastUser(username: String?) {
        if (username != null) {
            output?.presentLastUser(username)
        }
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