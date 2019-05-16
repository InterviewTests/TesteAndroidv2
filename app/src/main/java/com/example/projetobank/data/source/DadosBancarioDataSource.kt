package com.example.projetobank.data.source

import com.example.projetobank.data.model.Concentrador
import com.example.projetobank.data.model.DadosBancarioResposta
import com.example.projetobank.data.model.Statement

interface DadosBancarioDataSource {
    fun pegaDadosBancario(idUsuario: Int, callbackResponse: CallbackResponse<DadosBancarioResposta>)
    fun salvaDadosStatement(
        statement: Statement
    )
}