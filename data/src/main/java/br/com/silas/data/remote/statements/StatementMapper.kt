package br.com.silas.data.remote.statements

import br.com.silas.domain.ErrorResponse
import br.com.silas.domain.statements.Statements

class StatementMapper {
    fun mapperStatementsEntityToStatements(statementsResponse: StatementsResponse): Pair<ErrorResponse, List<Statements>> {

        val statementsList = mutableListOf<Statements>()
        statementsResponse.statements.forEach { statementsEntity ->
                statementsList.add(
                Statements(
                    statementsEntity.title,
                    statementsEntity.desc,
                    statementsEntity.date,
                    statementsEntity.value
                )
            )
        }

        val error = ErrorResponse(
            statementsResponse.errorResponse.code,
            statementsResponse.errorResponse.message
        )

        return Pair(error, statementsList)
    }
}