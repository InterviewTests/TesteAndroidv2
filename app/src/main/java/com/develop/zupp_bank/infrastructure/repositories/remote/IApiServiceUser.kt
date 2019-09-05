package com.develop.zupp_bank.infrastructure.repositories.remote

import com.develop.zupp_bank.domain.models.UserAccount
import com.develop.zupp_bank.domain.models.User
import com.develop.zupp_bank.infrastructure.utils.ReturnAPI
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IApiServiceUser {

    @POST("api/login")
    fun login(@Body user: User): Call<ReturnAPI<UserAccount>>

}