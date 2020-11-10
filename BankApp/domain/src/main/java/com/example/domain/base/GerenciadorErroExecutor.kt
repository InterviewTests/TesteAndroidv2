package com.example.domain.base

import com.example.domain.excecoes.ExecutorException
import java.util.concurrent.TimeoutException

class GerenciadorErroExecutor {
    companion object {
        @Throws(Exception::class)
        fun tratarExcecao(
            tag: TagExcecao = TagExcecao.NAO_IDENTIFICADO,
            excecao: Exception
        ) = when (excecao::class.java) {
            TimeoutException::class.java ->
                excecao
            else -> ExecutorException(tag, excecao.message)
        }
    }
}