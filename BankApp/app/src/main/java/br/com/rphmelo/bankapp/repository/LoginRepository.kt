package br.com.rphmelo.bankapp.repository

import br.com.rphmelo.bankapp.api.LoginService
import br.com.rphmelo.bankapp.models.LoginRequest
import br.com.rphmelo.bankapp.models.LoginResponse
import retrofit2.Call

class LoginRepository(private val loginService: LoginService) {
    fun login(request: LoginRequest): Call<LoginResponse> {
        return loginService.login(request)
    }

}