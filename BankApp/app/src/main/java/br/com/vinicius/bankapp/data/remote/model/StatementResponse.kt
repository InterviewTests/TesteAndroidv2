package br.com.vinicius.bankapp.data.remote.model


data class StatementResponse(
    val error: ErrorResponse,
    val statementList: List<StatementModel>
)