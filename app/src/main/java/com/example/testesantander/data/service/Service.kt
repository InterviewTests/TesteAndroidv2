package com.example.testesantander.data.service

import com.example.testesantander.domain.model.StatementsResponse
import com.example.testesantander.domain.model.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface Service{
    @FormUrlEncoded
    @POST("login")
    fun getUser(
        @Field("user")
        user: String,
        @Field("password")
        password: String
    ) : Call<UserResponse>

    @GET("statements/1")
    fun getStatements(
    ): Call<StatementsResponse>
}