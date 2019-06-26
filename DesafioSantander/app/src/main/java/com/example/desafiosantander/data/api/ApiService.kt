package com.example.desafiosantander.data.api

import com.example.desafiosantander.data.model.request.LoginRequest
import com.example.desafiosantander.data.model.response.LoginResponse
import com.example.desafiosantander.data.model.response.StatementResponse
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("api/login")
    fun login(@Body loginRequest: LoginRequest): Flowable<LoginResponse>

    @GET("api/statements/{idUser}")
    fun statementList(@Path("idUser") idUser: Int): Flowable<StatementResponse>
}