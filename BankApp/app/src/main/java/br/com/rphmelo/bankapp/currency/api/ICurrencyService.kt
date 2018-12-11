package br.com.rphmelo.bankapp.currency.api

import br.com.rphmelo.bankapp.currency.domain.models.StatementResponse
import retrofit2.Call
import retrofit2.http.*

interface ICurrencyService {

    @GET("statements/{userId}")
    fun statements(@Path("userId") userId: Int): Call<StatementResponse>
}