package br.com.bank.android.core.retrofit.stataments

import br.com.bank.android.core.retrofit.stataments.response.StatamentsBodyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BankStataments {

    @GET("statements/{idUser}")
    suspend fun onStatements(@Path("idUser") idUser: Int): Response<StatamentsBodyResponse>
}