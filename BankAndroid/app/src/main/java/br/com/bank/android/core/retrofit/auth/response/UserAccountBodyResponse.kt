package br.com.bank.android.core.retrofit.auth.response

import br.com.bank.android.core.retrofit.ErrorResponse

data class UserAccountBodyResponse(
    val userAccount: UserAccountResponse,
    val error: ErrorResponse
)