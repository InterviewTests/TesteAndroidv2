package br.com.mdr.testeandroid.model.business

data class Error(
    val errorType: ErrorType,
    val param: String? = null,
    val messageId: String = ""
)