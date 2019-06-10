package com.zuptest.santander.login

import com.zuptest.santander.data.remote.mapper.mapToEntity
import com.zuptest.santander.data.remote.response.AccountResponse

const val INVALID_PASSWORD = "abc"
const val VALID_PASSWORD = "Test@1"
const val EMAIL = "email.valido@teste.com"

val LOGIN_RESPONSE =
    AccountResponse(
        userId = 1,
        userName = "José Teste da Silva Sauro",
        balance = 1000.0,
        bankAccount = "0987654321",
        bankAgency = "0001"
    ).mapToEntity()
