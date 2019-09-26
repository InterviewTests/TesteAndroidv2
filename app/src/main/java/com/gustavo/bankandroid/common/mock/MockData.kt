package com.gustavo.bankandroid.common.mock

import com.gustavo.bankandroid.datasource.data.statement.gson.StatementList
import com.gustavo.bankandroid.datasource.data.statement.gson.StatementResponse
import com.gustavo.bankandroid.datasource.data.user.gson.ServerLoginResponse
import com.gustavo.bankandroid.datasource.data.user.gson.UserAccount
import com.gustavo.bankandroid.datasource.database.dto.UserInfoDto
import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.entity.UserStatementItem

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
        com.gustavo.bankandroid.datasource.data.user.gson.Error(0, null)
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