package br.com.rphmelo.bankapp.login.domain.interactor

import br.com.rphmelo.bankapp.common.extensions.isEmail
import br.com.rphmelo.bankapp.common.extensions.isValidCPF
import br.com.rphmelo.bankapp.common.extensions.isValidPassword
import br.com.rphmelo.bankapp.login.domain.models.LoginRequest
import br.com.rphmelo.bankapp.login.domain.models.LoginResponse
import br.com.rphmelo.bankapp.login.domain.models.OnLoginFinishedListener
import br.com.rphmelo.bankapp.login.repository.LoginRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginInteractor(private val loginRepository: LoginRepository) {

    fun login(loginRequest: LoginRequest, listener: OnLoginFinishedListener) {
        val user = loginRequest.user
        val password = loginRequest.password

        when {
            user.isEmpty() -> listener.onUserEmptyError()
            password.isEmpty() -> listener.onPasswordEmptyError()
            !validUser(user) -> listener.onUserInvalidError()
            !password.isValidPassword() -> listener.onPasswordInvalidError()
            else -> {
                loginRepository.login(loginRequest).enqueue(object: Callback<LoginResponse?> {
                    override fun onResponse(call: Call<LoginResponse?>?,
                                            response: Response<LoginResponse?>?) {
                        val response: LoginResponse? = response?.body()
                        response?.let { listener.onLoginSuccess(it) }
                    }
                    override fun onFailure(call: Call<LoginResponse?>?, t: Throwable?) {

                    }
                })
            }
        }
    }

    private fun validUser(user: String): Boolean {
        return user.isEmail() || user.isValidCPF()
    }

}