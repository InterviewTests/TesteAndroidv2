package pt.felipegouveia.bankapp.data.statements.model

import pt.felipegouveia.bankapp.domain.model.common.Error
import pt.felipegouveia.bankapp.domain.model.statements.Statement
import pt.felipegouveia.bankapp.domain.model.statements.Statements

class StatementsMapper {

    /**
     * Map statements from data entity to domain entity
     */
    private fun mapStatementsListDataEntityToDomainEntity(statementsList: List<StatementData>?)
            : List<Statement> = statementsList?.map {
        mapStatementDataEntityToDomainEntity(it)
    } ?: emptyList()

    private fun mapStatementDataEntityToDomainEntity(data: StatementData): Statement =
        Statement (
            title = data.title,
            desc = data.desc,
            date = data.date,
            value = data.value
        )

    fun mapStatementsDataEntityToDomainEntity(response: StatementsData): Statements =
        Statements(
            statementList = mapStatementsListDataEntityToDomainEntity(response.statementDataList),
            error = Error(response.error?.message)
        )
}