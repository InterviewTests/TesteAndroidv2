package com.example.ilealnod.SantanderProjectKotlin.Todos.ChamadaApi

import com.example.ilealnod.SantanderProjectKotlin.Todos.Dependencias.AccountInfoResponse
import com.example.ilealnod.SantanderProjectKotlin.Todos.Dependencias.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface GetCredentials {

    // Implementação dos metodos GET /POST e de validação da chamada

    @POST("login")
    @FormUrlEncoded

    fun verifyLogin(
        @Field("user")        login: String,
        @Field("password") password: String ): Call<LoginResponse>

    @GET
    fun getAccountInfo(@Url url:String): Call<AccountInfoResponse>
}