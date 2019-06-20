package br.com.vinicius.bankapp.data.remote.model


data class StatementModel(
    val date: String,
    val desc: String,
    val title: String,
    val value: Double
)