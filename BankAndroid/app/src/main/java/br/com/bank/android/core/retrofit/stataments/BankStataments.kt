package br.com.bank.android.core.retrofit.stataments

import br.com.bank.android.core.retrofit.stataments.response.StatamentsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BankStataments {

    @GET("statements/{idUser}")
    suspend fun onStatements(@Path("idUser") idUser: String): Response<List<StatamentsResponse>>
}