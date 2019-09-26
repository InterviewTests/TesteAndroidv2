package com.gustavo.bankandroid.features.login.data.mapper

import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.features.login.data.dto.UserInfoDto

class UserInfoMapper {
    fun toDto(userInfo: UserInfo) =
        UserInfoDto(
            userInfo.userId,
            userInfo.name,
            userInfo.bankAccount,
            userInfo.agency,
            userInfo.balance
        )

    fun toUserInfo(userInfoDto: UserInfoDto)=
        UserInfo(
            userInfoDto.userId,
            userInfoDto.name,
            userInfoDto.bankAccount,
            userInfoDto.agency,
            userInfoDto.balance
        )
}