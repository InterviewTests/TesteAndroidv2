package com.example.data.api.base.excecoes

open class ApiException(
    mensagem: String? = null,
    causa: Throwable? = null
) :
    Exception(mensagem ?: causa?.message, causa)