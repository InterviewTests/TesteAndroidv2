package com.example.otavioaugusto.testesantander

import com.example.otavioaugusto.testesantander.model.User
import com.example.otavioaugusto.testesantander.model.UserResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIService {

    @POST("api/login")
    @FormUrlEncoded
    fun login(@Field("user") user: String,
              @Field("password") password: String): Call<UserResponse>
}
