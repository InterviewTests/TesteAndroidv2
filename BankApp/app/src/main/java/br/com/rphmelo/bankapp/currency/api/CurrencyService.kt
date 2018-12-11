package br.com.rphmelo.bankapp.currency.api

import br.com.rphmelo.bankapp.common.api.CoreApi
import br.com.rphmelo.bankapp.currency.domain.models.StatementResponse
import retrofit2.Call

class CurrencyService : ICurrencyService {

    private val coreApi = CoreApi().retrofit()
    private val currencyService = coreApi.create(ICurrencyService::class.java)

    override fun statements(userId: Int): Call<StatementResponse> {
        return currencyService.statements(userId)
    }
}