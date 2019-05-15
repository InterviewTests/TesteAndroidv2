package com.example.projetobank.data.source.remote

import com.example.projetobank.data.model.userAccount
import com.example.projetobank.data.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UsuarioService {
    @POST("login")
    fun requestDadosBancario(@Body filtro: Usuario): Call<userAccount>
}