package com.gustavo.bankandroid.mock

import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.entity.UserStatementItem
import com.gustavo.bankandroid.features.login.data.dto.UserInfoDto
import com.gustavo.bankandroid.features.login.data.gson.ServerLoginResponse
import com.gustavo.bankandroid.features.login.data.gson.UserAccount
import com.gustavo.bankandroid.features.statements.data.gson.StatementList
import com.gustavo.bankandroid.features.statements.data.gson.StatementResponse

object MockData {
    fun mockUserInfo() = UserInfo(1, "name", 1234, 1212, 1000.12)
    fun mockUserInfoDto() = UserInfoDto(1, "name", 1234, 1212, 1000.12)
    fun mockUserAccount() = UserAccount(1, "name", 1234, 1212, 1000.12)

    fun mockStatementList() = listOf(
        UserStatementItem("title1", "desc1", "date", 100.0),
        UserStatementItem("title2", "desc2", "date", 200.0),
        UserStatementItem("title3", "desc3", "date", 3300.0)
    )

    fun mockServerLoginResponse() = ServerLoginResponse(
        mockUserAccount(),
        com.gustavo.bankandroid.features.login.data.gson.Error(0, null)
    )

    fun mockStatementResponse() = StatementResponse(
        listOf(
            StatementList("title1", "desc1", "date", 100.0),
            StatementList("title2", "desc2", "date", 200.0),
            StatementList("title3", "desc3", "date", 3300.0)
        ),
        Error()
    )
}