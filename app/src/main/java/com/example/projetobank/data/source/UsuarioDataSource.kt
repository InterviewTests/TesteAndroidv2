package com.example.projetobank.data.source

import com.example.projetobank.data.model.Concentrador
import com.example.projetobank.data.model.DadosBancario
import com.example.projetobank.data.model.UsuarioResposta

interface UsuarioDataSource {

    fun pegaUsuario(concentrador: Concentrador?, callbackResponse: CallbackResponse<UsuarioResposta>)

    fun deletaUsuario(acao: () -> Unit)

    fun salvaDadosDeAutenticacao(
        dadosBancario: DadosBancario
    )
}

