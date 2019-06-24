package com.earaujo.santander.login

import android.content.Context
import com.earaujo.santander.R
import com.earaujo.santander.repository.LoginRepositoryCallback
import com.earaujo.santander.repository.LoginRepositoryImpl
import com.earaujo.santander.repository.Resource
import com.earaujo.santander.repository.models.LoginRequest
import com.earaujo.santander.repository.models.LoginResponse
import com.earaujo.santander.util.Preferences
import com.earaujo.santander.util.isValidPassword
import com.earaujo.santander.util.isValidUsername

class LoginInteractor(private val context: Context) : LoginInteractorInput {

    lateinit var loginPresenterInput: LoginPresenterInput
    val loginRepository = LoginRepositoryImpl()

    override fun performLogin(loginRequest: LoginRequest) {
        if (!loginRequest.user.isValidUsername()) {
            loginPresenterInput.presentLoginResponse(
                Resource.error(context.getString(R.string.login_error_message_invalid_username), null))
            return
        }

        if (!loginRequest.password.isValidPassword()) {
            loginPresenterInput.presentLoginResponse(
                Resource.error(context.getString(R.string.login_error_message_invalid_password), null))
            return
        }

        loginRepository.fetchLogin(loginRequest, object : LoginRepositoryCallback {
            override fun onData(loginResponse: Resource<LoginResponse>) {
                loginPresenterInput.presentLoginResponse(loginResponse)
            }
        })
    }

    override fun checkUserLoggedIn() {
        val userName = Preferences.getUserName()
        if (userName.isNotBlank()) {
            loginPresenterInput.presentSetUserName(userName)
        }
    }
}

interface LoginInteractorInput {
    fun performLogin(loginRequest: LoginRequest)
    fun checkUserLoggedIn()
}