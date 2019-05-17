package com.example.testeandroidv2.service

import com.example.testeandroidv2.model.login.LoginBody
import com.example.testeandroidv2.model.login.LoginResponse
import com.example.testeandroidv2.model.statement.StatementResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Services {

    @POST("login")
    fun login(@Body loginBody: LoginBody): Call<LoginResponse>?

    @GET("statements/{idUser}")
    fun getStatement(@Path("idUser") idUser: Int): Call<StatementResponse>?
}