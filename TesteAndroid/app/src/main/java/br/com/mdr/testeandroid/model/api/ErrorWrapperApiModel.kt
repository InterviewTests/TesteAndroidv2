package br.com.mdr.testeandroid.model.api

data class ErrorWrapperApiModel (
    val status: Int? = null,
    val errors: MutableList<ErrorApiModel>? = null
)