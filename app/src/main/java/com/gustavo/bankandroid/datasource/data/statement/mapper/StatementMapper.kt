package com.gustavo.bankandroid.datasource.data.statement.mapper

import com.gustavo.bankandroid.datasource.data.statement.gson.StatementResponse
import com.gustavo.bankandroid.entity.UserStatementItem

class StatementMapper {
    fun gsonToEntity(response: StatementResponse): List<UserStatementItem> {
        return response.statementList.map{
            UserStatementItem(it.title, it.desc, it.date, it.value)
        }
    }
}