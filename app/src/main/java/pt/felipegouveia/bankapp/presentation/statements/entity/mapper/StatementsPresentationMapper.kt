package pt.felipegouveia.bankapp.presentation.statements.entity.mapper

import pt.felipegouveia.bankapp.domain.common.Mapper
import pt.felipegouveia.bankapp.domain.model.statements.Statements
import pt.felipegouveia.bankapp.presentation.statements.entity.StatementsPresentation

class StatementsPresentationMapper : Mapper<Statements, StatementsPresentation>() {

    override fun mapFrom(data: Statements): StatementsPresentation =
        mapStatementsToPresentation(data)

    private fun mapStatementsToPresentation(domain: Statements): StatementsPresentation =
        StatementsPresentation(

        )
}