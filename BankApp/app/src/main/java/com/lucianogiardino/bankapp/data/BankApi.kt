package com.lucianogiardino.bankapp.data

import com.lucianogiardino.bankapp.domain.model.LoginResponse
import com.lucianogiardino.bankapp.domain.model.StatementResponse
import retrofit2.Call
import retrofit2.http.*

interface BankApi {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("user") user:String,
        @Field("password")  password:String
    ): Call<LoginResponse>

    @GET("statements/{userId}")
    fun fetchStatement(
        @Path("userId") userId: Int
    ): Call<StatementResponse>



}