package br.com.rphmelo.bankapp.currency.domain.interactor

import br.com.rphmelo.bankapp.currency.domain.models.OnCurrencyLoadDataListener
import br.com.rphmelo.bankapp.currency.domain.models.StatementResponse
import br.com.rphmelo.bankapp.currency.repository.CurrencyRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyInteractor(private val currencyRepository: CurrencyRepository) {

    fun statements(userId: Int, listener: OnCurrencyLoadDataListener) {
        currencyRepository.statements(userId).enqueue(object: Callback<StatementResponse?> {
            override fun onResponse(call: Call<StatementResponse?>?,
                                    response: Response<StatementResponse?>?) {
                val response: StatementResponse? = response?.body()
                response?.let { listener.onLoadStatementListSuccess(it) }
            }
            override fun onFailure(call: Call<StatementResponse?>?, t: Throwable?) {
                listener.onLoadStatementListError()
            }
        })
    }

    fun setupToolbar(listener: OnCurrencyLoadDataListener) {
        listener.onSetupToolbar()
    }

}