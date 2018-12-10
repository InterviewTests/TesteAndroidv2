package com.accenture.primo.bankapp.data.network

import com.accenture.primo.bankapp.ui.login.LoginModel
import com.accenture.primo.bankapp.ui.main.MainModel
import io.reactivex.Observable
import retrofit2.http.*

interface IServices {
    @FormUrlEncoded()
    @POST("login")
    fun doLogin(@Field("user") user: String, @Field("password") password: String): Observable<LoginModel.LoginResponse>

    @GET("statements/{id}")
    fun getUserStatements(@Path("id") id:Long): Observable<MainModel.MainResponse>
}