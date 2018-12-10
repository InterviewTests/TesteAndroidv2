package com.example.otavioaugusto.testesantander.utils

import com.example.otavioaugusto.testesantander.model.StatementListItem
import com.example.otavioaugusto.testesantander.model.Statements
import com.example.otavioaugusto.testesantander.model.User
import com.example.otavioaugusto.testesantander.model.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface APIService {

    @POST("api/login")
    @FormUrlEncoded
    fun login(@Field("user") user: String,
              @Field("password") password: String): Call<UserResponse>

    @GET("api/statements/{id}")
    fun getStatements(@Path("id") id:String): Call<Statements>
}
