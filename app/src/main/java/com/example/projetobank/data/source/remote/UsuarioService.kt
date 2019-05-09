package com.example.projetobank.data.source.remote

import com.example.projetobank.data.model.Concentrador
import com.example.projetobank.data.model.DadosBancario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UsuarioService {
    @POST("login")
    fun requestDadosBancario(@Body filtro: Concentrador): Call<DadosBancario>
}