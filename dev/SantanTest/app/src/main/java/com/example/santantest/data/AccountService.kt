package com.example.santantest.data

import com.example.santantest.domain.model.LoginResponse
import com.example.santantest.domain.model.StatementResponse

import retrofit2.Call
import retrofit2.http.*

interface AccountService {

    @GET("/api/statements/{id}")
    fun getStatements(@Path("id") userID: Int): Call<StatementResponse>

    @FormUrlEncoded
    @POST("/api/login")
    fun login(@Field("user") login: String, @Field("password") pass: String): Call<LoginResponse>


}