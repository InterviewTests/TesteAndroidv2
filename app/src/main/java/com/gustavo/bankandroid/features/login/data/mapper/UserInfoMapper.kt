package com.gustavo.bankandroid.features.login.data.mapper

import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.features.login.data.dto.UserInfoDto

class UserInfoMapper {
    fun toDto(userInfo: UserInfo) =
        UserInfoDto(
            userInfo.id,
            userInfo.username,
            userInfo.password,
            userInfo.name,
            userInfo.account,
            userInfo.balance
        )

    fun toUserInfo(userInfoDto: UserInfoDto)=
        UserInfo(
            userInfoDto.id,
            userInfoDto.username,
            userInfoDto.password,
            userInfoDto.name,
            userInfoDto.account,
            userInfoDto.balance
        )
}