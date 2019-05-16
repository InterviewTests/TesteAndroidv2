package com.example.projetobank.data.source

import com.example.projetobank.data.model.DadosBancarioResposta
import com.example.projetobank.data.model.Statement
import com.example.projetobank.data.source.remote.DadosBancarioRemoteDataSource

class DadosBancarioRepositorio
    (  private val remoteDataSource: DadosBancarioRemoteDataSource)
    : DadosBancarioDataSource{
    override fun pegaDadosBancario(
        idUsuario: Int,
        callbackResponse: CallbackResponse<DadosBancarioResposta>
    ) {
        idUsuario?.let {
            remoteDataSource.pegaDadosBancario(idUsuario, object : CallbackResponse<DadosBancarioResposta> {
                override fun sucesso(response: DadosBancarioResposta) {
                    salvaDadosStatement(
                        response.statement.get(0)
                    )
                }
                override fun erro() {
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