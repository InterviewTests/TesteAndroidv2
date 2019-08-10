package com.example.projetobank.data.source.remote

import com.example.projetobank.data.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface DadosBancarioService {
    @GET("statements/{statements}")
    fun requestDadosBancario(@Path("statements")filtro: Int): Call<statementList>
}