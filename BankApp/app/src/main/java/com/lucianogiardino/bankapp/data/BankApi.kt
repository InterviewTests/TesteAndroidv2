package com.lucianogiardino.bankapp.data

import com.lucianogiardino.bankapp.login.domain.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BankApi {
    @FormUrlEncoded
    @POST("/login")
    fun login(
        @Field("user") user:String,
        @Field("password")  password:String
    ): Call<LoginResponse>
}