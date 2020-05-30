package com.example.data.api

import com.example.data.data.ListaStatementsData
import com.example.data.data.LoginRequisicaoData
import com.example.data.data.LoginRespostaData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BankApi {
    @POST("login")
    fun realizarLogin(@Body loginRequisicaoData: LoginRequisicaoData): Call<LoginRespostaData>

    @GET("statements/{id}")
    fun listarStatementsPorUsuario(@Path("id") id: Int?): Call<ListaStatementsData>
}