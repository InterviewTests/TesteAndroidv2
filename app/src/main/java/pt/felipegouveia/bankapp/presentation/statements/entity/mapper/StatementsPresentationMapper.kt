package pt.felipegouveia.bankapp.presentation.statements.entity.mapper

import pt.felipegouveia.bankapp.domain.common.Mapper
import pt.felipegouveia.bankapp.domain.model.statements.Statement
import pt.felipegouveia.bankapp.domain.model.statements.Statements
import pt.felipegouveia.bankapp.presentation.entity.Error
import pt.felipegouveia.bankapp.presentation.statements.entity.StatementPresentation
import pt.felipegouveia.bankapp.presentation.statements.entity.StatementsPresentation

class StatementsPresentationMapper : Mapper<Statements, StatementsPresentation>() {

    /**
     * Map statements from domain entity to presentation entity
     */
    override fun mapFrom(data: Statements): StatementsPresentation =
        mapStatementsToPresentation(data)

    private fun mapDomainListItemToPresentation(statementList: List<Statement>?)
            : List<StatementPresentation> = statementList?.map { mapDomainItemToPresentation(it) }
        ?: emptyList()

    private fun mapDomainItemToPresentation(domain: Statement): StatementPresentation =
        StatementPresentation(
            title = domain.title,
            desc = domain.desc,
            date = domain.date,
            value = domain.value
        )

    private fun mapStatementsToPresentation(domain: Statements): StatementsPresentation =
        StatementsPresentation(
            statementList = mapDomainListItemToPresentation(domain.statementList),
            error = Error(
                domain.error?.message,
                domain.error?.stringId
            )
        )
}