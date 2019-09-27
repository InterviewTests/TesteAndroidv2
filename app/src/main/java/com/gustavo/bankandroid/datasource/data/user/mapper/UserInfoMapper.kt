package com.gustavo.bankandroid.datasource.data.user.mapper

import com.gustavo.bankandroid.datasource.database.dto.UserInfoDto
import com.gustavo.bankandroid.entity.UserInfo

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