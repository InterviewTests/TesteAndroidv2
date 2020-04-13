package com.example.testeandroidv2.data.repository.login

import com.example.testeandroidv2.domain.login.UserAccount

sealed class LoginResult {
    class Success(val userAccounts: UserAccount) : LoginResult()
    class ApiError(val statusCode: Int) : LoginResult()
    object ServerError : LoginResult()
}