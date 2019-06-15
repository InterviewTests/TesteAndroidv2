package com.accenture.santander.remote.iservice

import com.accenture.santander.entity.Auth
import com.accenture.santander.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.http.*

interface IUser {
    @FormUrlEncoded
    @POST("login")
    fun login(@Field("user") user: String, @Field("password") password: String): Call<Auth>
}