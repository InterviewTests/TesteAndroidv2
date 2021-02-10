package com.br.myapplication.data.service

import com.br.myapplication.data.model.ResponseLogin
import com.br.myapplication.data.model.ResponseStatement
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Path


interface Requests {

    @POST("login")
    @FormUrlEncoded
    suspend fun loginAsync(@Field("user") user: String, @Field("password") password:String): ResponseLogin

    @GET("statements/{id}")
    suspend fun getStatements(@Path("id") id: String): ResponseStatement
}