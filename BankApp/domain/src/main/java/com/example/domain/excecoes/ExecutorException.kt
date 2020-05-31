package com.example.domain.excecoes

import com.example.domain.base.TagExcecao

open class ExecutorException(
    val tag: TagExcecao = TagExcecao.NAO_IDENTIFICADO,
    val mensagem: String? = null,
    causa: Throwable? = null
) :
    Exception(mensagem ?: causa?.message, causa)