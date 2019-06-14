package com.accenture.santander.remote.iservice

import com.accenture.santander.entity.Auth
import com.accenture.santander.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.http.*

interface IUser {
    @Headers("Content-Type: application/json")
    @POST("login")
    fun login(@Body user: UserViewModel): Call<Auth>
}