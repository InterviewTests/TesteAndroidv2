package com.example.santantest.data

import com.example.santantest.domain.model.LoginResponse

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AccountService {

    @FormUrlEncoded
    @POST("/api/login")
    fun login(@Field("user") login: String, @Field("password") pass: String): Call<LoginResponse>


}