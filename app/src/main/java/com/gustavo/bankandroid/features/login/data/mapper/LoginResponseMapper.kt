package com.gustavo.bankandroid.features.login.data.mapper

import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.entity.UserLoginResponse
import com.gustavo.bankandroid.features.login.data.gson.ServerLoginResponse

class LoginResponseMapper {

    fun fromServer(serverLoginResponse: ServerLoginResponse): UserLoginResponse =
        if (serverLoginResponse.error.message.isNullOrBlank()){
            val userAccount = serverLoginResponse.userAccount
            UserLoginResponse.Success(UserInfo(
                userAccount.userId,
                userAccount.name,
                userAccount.bankAccount,
                userAccount.agency,
                userAccount.balance
            ))
        }else{
            UserLoginResponse.Error
        }
}