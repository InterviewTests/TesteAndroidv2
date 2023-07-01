package com.nandoligeiro.safrando.domain.login.repository

import com.nandoligeiro.safrando.domain.login.model.LoginDomain

interface LoginRepository {
    suspend fun postLogin(user: String, password: String): LoginDomain
}
