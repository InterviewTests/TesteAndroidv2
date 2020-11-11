package com.example.domain.entities

data class LoginResposta(
    val contaUsuario: ContaUsuario?,
    val error: Erro?
)