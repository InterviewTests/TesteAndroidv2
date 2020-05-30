package com.example.data.api.base.excecoes

import com.example.data.util.GerenciadorLog

open class RepositorioException(
    tag: String,
    mensagem: String? = null,
    complemento: String? = null,
    causa: Throwable? = null
) :
    Exception(mensagem ?: causa?.message, causa) {
    init {
        GerenciadorLog.logarErro(tag, mensagem, complemento, causa)
    }
}