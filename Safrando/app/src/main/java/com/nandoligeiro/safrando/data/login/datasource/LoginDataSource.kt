package com.nandoligeiro.safrando.data.login.datasource

import com.nandoligeiro.safrando.data.login.model.LoginData

interface LoginDataSource {
    suspend fun postLogin(user: String, password: String): LoginData
}