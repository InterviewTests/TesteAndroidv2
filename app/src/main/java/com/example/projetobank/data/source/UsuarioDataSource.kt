package com.example.projetobank.data.source

import com.example.projetobank.data.model.userAccount
import com.example.projetobank.data.model.Usuario

interface UsuarioDataSource {

    fun pegaUsuario(concentrador: Usuario?, callbackResponse: CallbackResponse<userAccount>)

    fun deletaUsuario(acao: () -> Unit)

    fun salvaDadosDeAutenticacao(
        usuario:Usuario?,
        userAccount: userAccount,
        sucesso: () -> Unit,
        erro: () -> Unit
    )
}

