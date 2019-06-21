package com.santander.data.source.remote

import com.santander.data.source.remote.entity.response.LoginResponse
import com.santander.data.source.remote.entity.response.StatementListResponse
import io.reactivex.Observable
import retrofit2.http.*

interface API {

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("user") user: String,
              @Field("password") password: String): Observable<LoginResponse>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @GET("statements/{userId}")
    fun fetchStatements(@Path("userId") userId: Int): Observable<StatementListResponse>
}