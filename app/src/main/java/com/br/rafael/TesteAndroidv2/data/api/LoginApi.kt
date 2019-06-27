package com.br.rafael.TesteAndroidv2.data.api

import retrofit2.Call
import com.br.rafael.TesteAndroidv2.data.model.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {

    @FormUrlEncoded
    @POST("login")
    fun userLogin(@Field("user") user: String, @Field("password") password: String): Call<LoginResponse>
}