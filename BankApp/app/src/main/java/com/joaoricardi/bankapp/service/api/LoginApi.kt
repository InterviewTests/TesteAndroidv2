package com.joaoricardi.bankapp.service.api

import com.joaoricardi.bankapp.models.login.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApi {

    @FormUrlEncoded
    @Headers( "content-type: application/x-www-form-urlencoded;charset=UTF-8" )
    @POST("login")
    fun login(
        @Field("user")  user: String,
        @Field("password") password: String): Deferred<LoginResponse>
}