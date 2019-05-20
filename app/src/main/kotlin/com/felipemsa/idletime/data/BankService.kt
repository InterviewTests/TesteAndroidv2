package com.felipemsa.idletime.data

import retrofit2.Call
import retrofit2.http.*

interface BankService {

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("user") user: String, @Field("password") pass: String): Call<LoginResponse>

    @GET("statements/{user_id}")
    fun statements(@Query("user_id") user: Int): Call<StatementsResponse>
}