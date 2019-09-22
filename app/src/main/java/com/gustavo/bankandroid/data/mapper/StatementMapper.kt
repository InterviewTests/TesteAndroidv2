package com.gustavo.bankandroid.data.mapper

import com.gustavo.bankandroid.data.gson.StatementResponse
import com.gustavo.bankandroid.entity.UserStatementItem

class StatementMapper {
    fun gsonToEntity(response: StatementResponse): List<UserStatementItem> {
        return response.statementList.map{
            UserStatementItem(it.title, it.desc, it.date, it.value)
        }
    }
}