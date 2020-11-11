package com.example.domain.repositories

import com.example.domain.entities.ListaStatements
import com.example.domain.entities.LoginRequisicao
import com.example.domain.entities.LoginResposta

interface IBankRepository {
    suspend fun realizarLogin(loginRequisicao: LoginRequisicao) : LoginResposta?
    suspend fun listarSatements(id : Int?) : ListaStatements
}