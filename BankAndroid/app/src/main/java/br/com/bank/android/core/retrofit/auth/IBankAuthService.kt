package br.com.bank.android.core.retrofit.auth

import br.com.bank.android.core.retrofit.auth.response.UserAccountResponse

interface IBankAuthService {
    suspend fun onLogin(user: String, password: String): UserAccountResponse
}