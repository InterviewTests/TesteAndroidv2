package com.example.projetobank.data.source

import com.example.projetobank.data.model.UsuarioResposta

interface DadosBancarioDataSource {
    fun pegaDadosBancario(callbackResponse: CallbackResponse<UsuarioResposta>)
}