package com.schaefer.bankapp.util.error

class ErrorHandler(private val code: Int) {
    fun getMessageFromCode(): String {
        var message = ""
        when (code) {
            1 -> message = "Ocorreu um erro inesperado. Por favor, tente novamente."
            2 -> message = "Problema na conexão. Por favor, verifique sua internet."
            else -> message = "Todo mundo falha... dessa vez foram nossos servidores. Por favor, tente nocamente."
        }

        return message
    }
}