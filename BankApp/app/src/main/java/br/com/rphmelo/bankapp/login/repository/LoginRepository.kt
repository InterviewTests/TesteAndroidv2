package br.com.rphmelo.bankapp.login.repository

import br.com.rphmelo.bankapp.login.api.LoginService
import br.com.rphmelo.bankapp.login.domain.models.LoginRequest
import br.com.rphmelo.bankapp.login.domain.models.LoginResponse
import retrofit2.Call

class LoginRepository(private val loginService: LoginService) {
    fun login(request: LoginRequest): Call<LoginResponse> {
        return loginService.login(request)
    }

}