package com.example.domain.entidades

data class LoginResposta(
    val contaUsuario: ContaUsuario,
    val error: Erro
)