package br.com.bank.android.core.retrofit.auth

import br.com.bank.android.core.BaseService
import br.com.bank.android.core.retrofit.CoreRetrofit
import br.com.bank.android.core.retrofit.auth.response.UserAccountResponse
import br.com.bank.android.exceptions.UndefinedException

class BankAuthService : BaseService(), IBankAuthService {

    override suspend fun onLogin(user: String, password: String): UserAccountResponse {
        try {
            val response = verifyResponse(
                CoreRetrofit.getBankAuth()
                    .onLogin(user, password)
            )
            return response?.userAccount ?: throw UndefinedException()
        } catch (error: Exception) {
            throw onError(error)
        }
    }
}