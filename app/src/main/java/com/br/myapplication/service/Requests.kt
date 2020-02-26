package com.br.myapplication.service

import com.br.myapplication.model.ResponseLogin
import com.br.myapplication.model.ResponseStatement
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Path


interface Requests {

    @POST("login")
    @FormUrlEncoded
    fun loginAsync(@Field("user") user: String, @Field("password") password:String): Call<ResponseLogin>

    @GET("statements/{id}")
    fun getStatements(@Path("id") id: String): Call<ResponseStatement>
}