package com.tata.bank.network

import com.tata.bank.login.LoginCredentials
import com.tata.bank.login.LoginResponse
import com.tata.bank.login.StatementResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @POST("login")
    fun login(@Body loginCredentials: LoginCredentials): Single<Response<LoginResponse>>

    @GET("statements/{idUser}")
    fun fetchStatements(
        @Path("idUser") idUser: Long
    ): Single<Response<StatementResponse>>
}