package com.example.projetobank.data.source.remote

import com.example.projetobank.data.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface DadosBancarioService {
    @GET("statements")
    fun requestDadosBancario(@Body filtro: Concentrador): Call<Statement>
}