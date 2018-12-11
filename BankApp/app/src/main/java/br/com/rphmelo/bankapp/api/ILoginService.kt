package br.com.rphmelo.bankapp.api

import br.com.rphmelo.bankapp.models.LoginRequest
import br.com.rphmelo.bankapp.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ILoginService {

    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}