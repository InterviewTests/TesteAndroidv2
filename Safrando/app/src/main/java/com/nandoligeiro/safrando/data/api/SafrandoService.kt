package com.nandoligeiro.safrando.data.api

import com.nandoligeiro.safrando.datasource.bankstatement.entity.BankStatementEntity
import com.nandoligeiro.safrando.datasource.login.entity.LoginEntity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SafrandoService {

    @POST("login")
    suspend fun postLogin(@Body user: String, @Body password: String): LoginEntity

    @GET("statements/{id}")
    suspend fun getBankStatements(@Path("id") userId: Int): List<BankStatementEntity>
}