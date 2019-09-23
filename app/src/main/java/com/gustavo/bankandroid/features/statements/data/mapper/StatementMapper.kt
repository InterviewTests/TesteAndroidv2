package com.gustavo.bankandroid.features.statements.data.mapper

import com.gustavo.bankandroid.entity.UserStatementItem
import com.gustavo.bankandroid.features.statements.data.gson.StatementResponse

class StatementMapper {
    fun gsonToEntity(response: StatementResponse): List<UserStatementItem> {
        return response.statementList.map{
            UserStatementItem(it.title, it.desc, it.date, it.value)
        }
    }
}