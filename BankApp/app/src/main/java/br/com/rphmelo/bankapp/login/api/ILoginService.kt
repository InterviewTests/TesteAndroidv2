package br.com.rphmelo.bankapp.login.api

import br.com.rphmelo.bankapp.login.domain.models.LoginRequest
import br.com.rphmelo.bankapp.login.domain.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ILoginService {

    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}