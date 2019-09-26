package com.gustavo.bankandroid.entity

sealed class UserLoginResponse {
    data class Success(val userInfo: UserInfo) : UserLoginResponse()
    object Error : UserLoginResponse()
}