package com.example.projetobank.data.source

import com.example.projetobank.data.model.userAccount
import com.example.projetobank.data.model.Usuario
import com.example.projetobank.data.model.UsuarioResposta

interface UsuarioDataSource {

    fun pegaUsuario(concentrador: Usuario?, callbackResponse: CallbackResponse<UsuarioResposta>)

    fun deletaUsuario(acao: () -> Unit)

    fun salvaDadosDeAutenticacao(
        usuario:Usuario?,
        userAccount: userAccount,
        sucesso: () -> Unit,
        erro: () -> Unit
    )
}

