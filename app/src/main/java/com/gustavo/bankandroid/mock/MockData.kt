package com.gustavo.bankandroid.mock

import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.entity.UserStatementItem
import com.gustavo.bankandroid.features.statements.data.gson.StatementList
import com.gustavo.bankandroid.features.statements.data.gson.StatementResponse

object MockData {
    fun mockUserInfo() = UserInfo(1, "username", "1234", "name", "account", 1000)

    fun mockStatementList() = listOf(
        UserStatementItem("title1", "desc1", "date", 100f),
        UserStatementItem("title2", "desc2", "date", 200f),
        UserStatementItem("title3", "desc3", "date", 3300f)
    )

    fun mockStatementResponso() = StatementResponse(
        listOf(
            StatementList("title1", "desc1", "date", 100f),
            StatementList("title2", "desc2", "date", 200f),
            StatementList("title3", "desc3", "date", 3300f)
        ),
        Error()
    )
}