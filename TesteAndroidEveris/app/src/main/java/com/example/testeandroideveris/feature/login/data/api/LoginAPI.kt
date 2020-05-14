package com.example.testeandroideveris.feature.login.data.api

import com.example.testeandroideveris.feature.login.data.LoginRequestData
import com.example.testeandroideveris.feature.login.data.LoginResponseData
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {

    @POST("login/")
    suspend fun login(@Body login: LoginRequestData): LoginResponseData

}