package br.com.bank.android.core.retrofit.stataments.response

import br.com.bank.android.core.retrofit.ErrorResponse

data class StatamentsBodyResponse(
    val statementList: List<StatamentsResponse>,
    val error: ErrorResponse
)