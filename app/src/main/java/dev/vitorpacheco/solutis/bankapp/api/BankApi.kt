package dev.vitorpacheco.solutis.bankapp.api

import retrofit2.Call
import retrofit2.http.*

interface BankApi {

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("user") user: String, @Field("password") password: String): Call<Any>

    @FormUrlEncoded
    @GET("statements/{userId}")
    fun listStatements(@Path("userId") userId: Int): Call<Any>

}