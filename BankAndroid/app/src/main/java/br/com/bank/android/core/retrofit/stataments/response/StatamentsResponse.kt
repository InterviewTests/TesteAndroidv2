package br.com.bank.android.core.retrofit.stataments.response

data class StatamentsResponse(
    val title: String,
    val desc: String,
    val date: String,
    val value: Double
)