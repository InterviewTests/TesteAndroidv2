package com.gustavo.bankandroid.datasource.data.user.mapper

import com.gustavo.bankandroid.datasource.data.user.gson.ServerLoginResponse
import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.entity.UserLoginResponse

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