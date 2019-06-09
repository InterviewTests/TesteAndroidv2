package com.schaefer.bankapp.service

import com.schaefer.bankapp.model.login.LoginModel
import com.schaefer.bankapp.model.login.LoginResult
import com.schaefer.bankapp.model.statement.StatementResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiProvider {
    @POST("login")
    fun makeLogin(@Body loginBody: LoginModel): Call<LoginResult>

    @GET("statements/{userId}")
    fun statementList(@Path("userId") userId: Int): Call<StatementResult>
}