package br.com.flaviokreis.santanderv2.data.model

data class Error(
    val code: Int = 200,
    val message: String?
)