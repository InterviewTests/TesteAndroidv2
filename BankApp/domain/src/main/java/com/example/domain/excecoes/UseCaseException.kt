package com.example.domain.excecoes

import com.example.domain.base.ExceptionTag

open class UseCaseException(
    val exceptionTag: ExceptionTag = ExceptionTag.NAO_IDENTIFICADO,
    val mensagem: String? = null,
    causa: Throwable? = null
) : Exception(mensagem ?: causa?.message, causa)