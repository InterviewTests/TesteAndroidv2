package br.com.bank.android.core.retrofit.stataments

import br.com.bank.android.core.BaseService
import br.com.bank.android.core.retrofit.CoreRetrofit
import br.com.bank.android.core.retrofit.stataments.response.StatamentsResponse
import br.com.bank.android.exceptions.UndefinedException

class BankStatamentsService : BaseService(), IBankStatamentsService {

    override suspend fun getStataments(idUser: String): List<StatamentsResponse> {
        try {
            val response = verifyResponse(
                CoreRetrofit.getBankStataments()
                    .onStatements(idUser)
            )
            return response ?: throw UndefinedException()
        } catch (error: Exception) {
            throw onError(error)
        }
    }
}