package com.example.projetobank.data.source

interface CallbackResponse<T> {

    fun sucesso(response: T)

    fun erro()
}