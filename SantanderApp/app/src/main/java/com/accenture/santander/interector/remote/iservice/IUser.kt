package com.accenture.santander.interector.remote.iservice

import com.accenture.santander.entity.Auth
import com.accenture.santander.entity.ListStatement
import retrofit2.Call
import retrofit2.http.*

interface IUser {
    @FormUrlEncoded
    @POST("login")
    fun login(@Field("user") user: String, @Field("password") password: String): Call<Auth>

    @GET("statements/{iduser}")
    fun statements(@Path("iduser") idUser: String): Call<ListStatement?>
}