package com.androiddeveloper.santanderTest.data.api.login

import com.androiddeveloper.santanderTest.data.model.userdata.LoginResponse
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginEndpoint {

    @POST("login")
    fun login(@Body userData: LoginRequest): Flowable<Response<LoginResponse>>
}