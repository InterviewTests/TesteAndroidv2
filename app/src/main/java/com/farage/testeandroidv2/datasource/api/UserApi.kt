package com.farage.testeandroidv2.datasource.api

import com.farage.testeandroidv2.datasource.model.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserApi {

    @FormUrlEncoded
    @POST("login")
    fun userLogin(@Field("user") user:String, @Field("password") password:String): Deferred<UserResponse>

}