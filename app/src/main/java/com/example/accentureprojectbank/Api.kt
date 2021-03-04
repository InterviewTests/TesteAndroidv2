package com.example.accentureprojectbank

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {
    companion object ServiceBuilder {
        /** Retorna uma Instância do Client Retrofit para Requisições
         * @param path Caminho Principal da API
         */
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private const val BASE_URL = "https://bank-app-test.herokuapp.com/api/"
    }

    @POST("login")
    fun fazerLogin(
        @Body bodyLog : bodyLogin
    ) : Call<UserAccountResponse>


    @GET("statements/{idUser}")
    fun dadosTransacoes(
            @Path("idUser")  idUsuaro : Int
    ) : Call<StatementsResponse>


}