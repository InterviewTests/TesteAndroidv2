package br.com.rphmelo.bankapp.api

import br.com.rphmelo.bankapp.models.LoginResponse
import retrofit2.Call
import retrofit2.http.GET

interface LoginService {

    @GET("/login")
    fun login(): Call<LoginResponse>
}