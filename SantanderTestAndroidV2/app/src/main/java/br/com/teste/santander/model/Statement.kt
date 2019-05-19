package br.com.teste.santander.model

data class Statement(
    val date: String,
    val desc: String,
    val title: String,
    val value: Double
)