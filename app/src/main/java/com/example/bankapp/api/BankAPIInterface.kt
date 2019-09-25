package com.example.bankapp.api

import com.example.bankapp.pojo.login.LoginPojo
import com.example.bankapp.pojo.statement.StatementPojo
import retrofit2.Call
import retrofit2.http.*

interface BankAPIInterface{
    @GET("statements/{userID}")
    fun getListStatements(@Path("userID") userID: Int): Call<StatementPojo>

    @FormUrlEncoded
    @POST("login")
    fun doLogin(@Field("password") password: String,
                @Field("user") user: String): Call<LoginPojo>
}