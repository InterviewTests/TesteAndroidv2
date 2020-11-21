package com.example.data.util

import android.util.Log

class LogManager {
    companion object {
        fun logarErro(
            tag: String,
            mensagem: String? = null,
            complemento: String? = null,
            excecao: Throwable? = null
        ) {
            val mensagemErro = "${mensagem ?: excecao?.message ?: ""} ${complemento ?: ""}"
            if (excecao != null) {
                Log.e(tag, mensagemErro, excecao)
            } else {
                Log.e(tag, mensagemErro)
            }
        }
    }
}