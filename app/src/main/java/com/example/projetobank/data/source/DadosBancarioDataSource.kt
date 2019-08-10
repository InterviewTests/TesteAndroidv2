package com.example.projetobank.data.source

import com.example.projetobank.data.model.Statement
import com.example.projetobank.data.model.statementList

interface DadosBancarioDataSource {
    fun pegaDadosBancario(concentrador: Int, callbackResponse: CallbackResponse<statementList>)
    fun salvaDadosStatement(
        statement: Statement
    )
}