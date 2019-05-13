package com.example.projetobank.data.source.remote

import com.example.projetobank.data.model.Concentrador
import com.example.projetobank.data.model.DadosBancarioResposta
import com.example.projetobank.data.model.Statement
import com.example.projetobank.data.model.UsuarioResposta
import com.example.projetobank.data.source.CallbackResponse
import com.example.projetobank.data.source.DadosBancarioDataSource
import com.example.projetobank.util.AppExecutors

class DadosBancarioRemoteDataSource

    ( val appExecutors: AppExecutors)

    : DadosBancarioDataSource{

    override fun pegaDadosBancario(
        concentrador: Concentrador?,
        callbackResponse: CallbackResponse<DadosBancarioResposta>
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun salvaDadosStatement(statement: Statement) {
    }


}