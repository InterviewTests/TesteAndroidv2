package com.example.data.data

import com.example.domain.entidades.LoginResposta
import com.google.gson.annotations.SerializedName

data class LoginRespostaData(
    @SerializedName("userAccount")
    val contaUsuario: ContaUsuarioData,
    @SerializedName("error")
    val erro: ErroData
)

fun LoginRespostaData.converterParaLoginResposta() =
    LoginResposta(contaUsuario.converterParaContaUsuario(), erro.converterParaErro())