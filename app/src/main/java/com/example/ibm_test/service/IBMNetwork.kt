package com.example.ibm_test.service

import com.example.ibm_test.data.LoginData
import com.example.ibm_test.data.UserItemData
import com.example.ibm_test.data.UserInfoData
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IBMNetwork {
    @POST("login")
    fun sendInfoToLogin(@Body data: LoginData): Single<UserInfoData>

    @GET("statements/{idUser}")
    fun getUserItemInfo(@Path("idUser") idUser: String): Single<List<UserItemData>>

}