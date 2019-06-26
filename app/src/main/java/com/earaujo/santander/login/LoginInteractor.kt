package com.earaujo.santander.login

import com.earaujo.santander.repository.LoginRepository
import com.earaujo.santander.repository.LoginRepositoryCallback
import com.earaujo.santander.repository.Resource
import com.earaujo.santander.repository.models.LoginRequest
import com.earaujo.santander.repository.models.LoginResponse
import com.earaujo.santander.util.Preferences

class LoginInteractor(
    private val loginRepository: LoginRepository,
    private val loginRequestValidator: LoginValidator
) : LoginInteractorInput {

    lateinit var loginPresenterInput: LoginPresenterInput

    override fun performLogin(loginRequest: LoginRequest) {
        if (!loginRequestValidator.isValidUsername(loginRequest.user)) {
            loginPresenterInput.presentLoginResponse(
                Resource.error(LoginInteractorErros.WRONG_USERNAME.errorNo, null))
            return
        }

        if (!loginRequestValidator.isValidPassword(loginRequest.password)) {
            loginPresenterInput.presentLoginResponse(
                Resource.error(LoginInteractorErros.WRONG_PASSWORD.errorNo, null))
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

enum class LoginInteractorErros(val errorNo: String){
    WRONG_USERNAME("invalid_username"),
    WRONG_PASSWORD("invalid_password")
}