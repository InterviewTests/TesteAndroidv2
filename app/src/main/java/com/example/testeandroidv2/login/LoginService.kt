package com.example.testeandroidv2.login

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("requestLogin")
    fun login(@Body loginBody: LoginBody): Call<LoginResponse>?
}