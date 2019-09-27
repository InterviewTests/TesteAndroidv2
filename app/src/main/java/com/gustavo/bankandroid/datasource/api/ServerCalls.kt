package com.gustavo.bankandroid.datasource.api

import com.gustavo.bankandroid.datasource.data.statement.gson.StatementResponse
import com.gustavo.bankandroid.datasource.data.user.gson.ServerLoginResponse
import com.gustavo.bankandroid.datasource.data.user.gson.UserLogin
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