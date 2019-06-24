package com.earaujo.santander.repository.rest

import com.earaujo.santander.repository.models.LoginResponse
import com.earaujo.santander.repository.models.StatementsResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("user") user: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @GET("statements/1")
    fun statements(
    ): Call<StatementsResponse>

}