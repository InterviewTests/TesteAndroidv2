package com.example.data.networking.base.exceptions

import com.example.data.util.LogManager

open class RepositoryException(
    tag: String,
    mensagem: String? = null,
    complemento: String? = null,
    causa: Throwable? = null
) :
    Exception(mensagem ?: causa?.message, causa) {
    init {
        LogManager.logarErro(tag, mensagem, complemento, causa)
    }
}