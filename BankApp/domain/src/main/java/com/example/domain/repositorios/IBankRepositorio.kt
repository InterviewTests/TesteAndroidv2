package com.example.domain.repositorios

import com.example.domain.entidades.ListaStatements
import com.example.domain.entidades.LoginRequisicao
import com.example.domain.entidades.LoginResposta
import com.example.domain.entidades.Statement

interface IBankRepositorio {
    suspend fun realizarLogin(loginRequisicao: LoginRequisicao) : LoginResposta?
    suspend fun listarSatements(id : Int?) : ListaStatements
}