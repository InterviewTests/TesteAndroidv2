package br.com.alex.bankappchallenge.network

import br.com.alex.bankappchallenge.network.model.LoginRequest
import br.com.alex.bankappchallenge.network.model.LoginResponse
import br.com.alex.bankappchallenge.network.model.StatementResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BankAPI {

    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>

    @GET("statements/{id}")
    fun fetchStatements(@Path("id") userId: Long): Single<StatementResponse>
}