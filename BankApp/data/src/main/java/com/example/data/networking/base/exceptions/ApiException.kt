package com.example.data.networking.base.exceptions

open class ApiException(
    mensagem: String? = null,
    causa: Throwable? = null
) : Exception(mensagem ?: causa?.message, causa)