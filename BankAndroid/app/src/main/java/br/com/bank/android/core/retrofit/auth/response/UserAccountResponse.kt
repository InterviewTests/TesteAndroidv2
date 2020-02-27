package br.com.bank.android.core.retrofit.auth.response

import br.com.bank.android.core.retrofit.ErrorResponse

data class UserAccountResponse(
    val userId: Int,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: Double,
    val error: ErrorResponse
)