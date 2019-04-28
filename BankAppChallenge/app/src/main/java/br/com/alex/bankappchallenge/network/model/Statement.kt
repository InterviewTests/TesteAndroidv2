package br.com.alex.bankappchallenge.network.model

data class StatementResponse(
    val statementList: List<Statement>,
    val error: Error
)

data class Statement(
    val title: String,
    val desc: String,
    val date: String,
    val value: Double
)