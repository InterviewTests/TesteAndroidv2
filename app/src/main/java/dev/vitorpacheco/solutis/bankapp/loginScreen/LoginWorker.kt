package dev.vitorpacheco.solutis.bankapp.loginScreen

import dev.vitorpacheco.solutis.bankapp.api.BankApi
import dev.vitorpacheco.solutis.bankapp.api.BankService.Companion.createService
import dev.vitorpacheco.solutis.bankapp.extensions.isValidCpf
import dev.vitorpacheco.solutis.bankapp.extensions.isValidEmail
import dev.vitorpacheco.solutis.bankapp.extensions.isValidPassword
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface LoginWorkerInput {
    fun login(request: LoginRequest, listener: (LoginResponse) -> Unit)
    fun validateUser(user: String): Boolean
    fun validatePassword(user: String): Boolean
}

class LoginWorker : LoginWorkerInput {

    var service: BankApi = createService()

    override fun login(request: LoginRequest, listener: (LoginResponse) -> Unit) {
        service.login(request.user, request.password)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    response.body()?.let {
                        listener(it)
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    listener(LoginResponse(error = UserError(t.message)))
                }
            })
    }

    override fun validateUser(user: String) = (user.isValidCpf() || user.isValidEmail())

    override fun validatePassword(user: String) = user.isValidPassword()

}