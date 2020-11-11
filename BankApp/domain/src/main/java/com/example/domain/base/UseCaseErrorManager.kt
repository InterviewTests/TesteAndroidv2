package com.example.domain.base

import com.example.domain.excecoes.UseCaseException
import java.util.concurrent.TimeoutException

class UseCaseErrorManager {
    companion object {
        @Throws(Exception::class)
        fun tratarExcecao(
            exceptionTag: ExceptionTag = ExceptionTag.NAO_IDENTIFICADO,
            excecao: Exception
        ) = when (excecao::class.java) {
            TimeoutException::class.java ->
                excecao
            else -> UseCaseException(exceptionTag, excecao.message)
        }
    }
}