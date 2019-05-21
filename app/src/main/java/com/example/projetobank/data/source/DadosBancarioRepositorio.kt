package com.example.projetobank.data.source

import android.util.Log
import com.example.projetobank.data.model.Statement
import com.example.projetobank.data.model.statementList
import com.example.projetobank.data.source.remote.DadosBancarioRemoteDataSource

class DadosBancarioRepositorio
    (  private val remoteDataSource: DadosBancarioRemoteDataSource)
    : DadosBancarioDataSource{
    override fun pegaDadosBancario(
        concentrador: Int,
        callbackResponse: CallbackResponse<statementList>
    ) {
        concentrador.let {
            remoteDataSource.pegaDadosBancario(concentrador, object : CallbackResponse<statementList> {
                override fun sucesso(response: statementList) {
                    callbackResponse.sucesso(response)
                }
                override fun erro() {
                    Log.e("erroRequisicao ", "repositorio")
                    callbackResponse.erro()
                }
            })
            return
        }
    }

    override fun salvaDadosStatement(statement: Statement) {
       remoteDataSource.salvaDadosStatement(statement)
    }
}