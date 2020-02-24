package br.com.flaviokreis.santanderv2.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import br.com.flaviokreis.santanderv2.data.network.BankService
import br.com.flaviokreis.santanderv2.data.preferences.LoginPreference
import br.com.flaviokreis.santanderv2.data.response.StatementsResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StatementRepository @Inject constructor(
    private val loginPreference: LoginPreference,
    private val bankService: BankService
) {

    fun getStatements(): LiveData<StatementsResponse> {
        val result = MediatorLiveData<StatementsResponse>()

        val request = bankService.statements(loginPreference.getUserAccount()?.userId ?: 0)
        result.addSource(request) {
            it.response?.body()?.let { response ->
                result.value = response
                result.removeSource(request)
            }
        }

        return result
    }

}