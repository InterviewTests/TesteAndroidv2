package com.example.data.model

import com.example.domain.entities.LoginResposta
import com.google.gson.annotations.SerializedName

data class LoginRespostaData(
    @SerializedName("userAccount")
    val contaUsuario: ContaUsuarioData,
    @SerializedName("error")
    val erro: ErroData
)

fun LoginRespostaData.toModel() =
    LoginResposta(contaUsuario.toModel(), erro.toModel())