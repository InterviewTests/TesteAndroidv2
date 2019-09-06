package com.develop.zupp_bank.infrastructure.repositories.remote

import com.develop.zupp_bank.domain.models.StatementList
import com.develop.zupp_bank.domain.models.UserAccount
import com.develop.zupp_bank.domain.models.User
import com.develop.zupp_bank.infrastructure.utils.ReturnAPI
import com.develop.zupp_bank.infrastructure.utils.ReturnStatement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IApiServiceUser {

    @POST("api/login")
    fun login(@Body user: User): Call<ReturnAPI<UserAccount>>

    @GET("api/statements/{idUser}")
    fun getStatement(@Path("idUser") idUser: Int): Call<ReturnStatement<List<StatementList>>>

}