package com.jeanjnap.data.mapper

import com.jeanjnap.data.model.response.StatementResponse
import com.jeanjnap.data.model.response.StatementSummaryResponse
import com.jeanjnap.domain.entity.Statement
import java.math.BigDecimal
import java.util.Date

class StatementSummaryResponseToStatementListMapperTest :
    BaseMapperTest<StatementSummaryResponse, List<Statement>>(
        StatementSummaryResponseToStatementListMapper::class.java.name
    ) {
    override fun mockClassIn() = StatementSummaryResponse(
        statementList = listOf(
            StatementResponse(
                title = "Pagamento",
                desc = "Conta de luz",
                date = "2021-01-17",
                value = BigDecimal.TEN
            ),
            StatementResponse(
                title = null,
                desc = null,
                date = null,
                value = null
            )
        )
    )

    override fun mockClassOut(): List<Statement> = listOf(
        Statement(
            title = "Pagamento",
            desc = "Conta de luz",
            date = Date(1610852400000),
            value = BigDecimal.TEN
        ),
        Statement(
            title = null,
            desc = null,
            date = null,
            value = null
        )
    )
}