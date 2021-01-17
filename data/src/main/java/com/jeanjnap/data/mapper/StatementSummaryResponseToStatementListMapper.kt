package com.jeanjnap.data.mapper

import com.jeanjnap.data.model.response.StatementSummaryResponse
import com.jeanjnap.domain.entity.Statement
import com.jeanjnap.domain.util.extensions.formatStringAsDate

class StatementSummaryResponseToStatementListMapper: Mapper<StatementSummaryResponse, List<Statement>> {
    override fun transform(item: StatementSummaryResponse?) = item?.statementList?.map {
        Statement(
            title = it.title,
            desc = it.desc,
            date = it.date?.formatStringAsDate(),
            value = it.value
        )
    } ?: emptyList()
}