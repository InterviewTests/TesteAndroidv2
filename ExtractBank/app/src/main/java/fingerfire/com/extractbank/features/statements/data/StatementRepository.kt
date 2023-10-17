package fingerfire.com.extractbank.features.statements.data

import fingerfire.com.extractbank.api.BankApi
import fingerfire.com.extractbank.network.ServiceState

class StatementRepository(private val bankApi: BankApi) {
    suspend fun getStatement(idUser: String): ServiceState<List<StatementsResponse>> {
        val response = bankApi.getStatement(idUser)
        return if (response.isSuccessful) {
            ServiceState.Success(response.body())
        } else {
            ServiceState.Error()
        }
    }
}