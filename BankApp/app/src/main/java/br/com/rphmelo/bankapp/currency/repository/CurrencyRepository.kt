package br.com.rphmelo.bankapp.currency.repository

import android.content.SharedPreferences
import br.com.rphmelo.bankapp.currency.api.CurrencyService
import br.com.rphmelo.bankapp.currency.domain.models.StatementResponse
import retrofit2.Call

class CurrencyRepository(private val currencyService: CurrencyService, private val preferences: SharedPreferences) {

    fun statements(userId: Int): Call<StatementResponse> {
        return currencyService.statements(userId)
    }
}