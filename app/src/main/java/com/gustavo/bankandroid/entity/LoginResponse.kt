package com.gustavo.bankandroid.entity

sealed class LoginResponse {
    data class Success(val userInfo: UserInfo) : LoginResponse()
    object Error : LoginResponse()
}