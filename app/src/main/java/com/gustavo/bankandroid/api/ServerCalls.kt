package com.gustavo.bankandroid.api

import com.gustavo.bankandroid.features.login.data.gson.ServerLoginResponse
import com.gustavo.bankandroid.features.login.data.gson.UserLogin
import com.gustavo.bankandroid.features.statements.data.gson.StatementResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServerCalls {

    @GET("statements/{idUser}")
    fun fetchStatements(@Path("idUser") id: Int): Single<StatementResponse>

    @POST("login")
    fun loginUser(@Body user: UserLogin): Single<ServerLoginResponse>
}