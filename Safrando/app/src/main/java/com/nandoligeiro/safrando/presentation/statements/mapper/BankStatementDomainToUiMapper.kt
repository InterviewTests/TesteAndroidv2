package com.nandoligeiro.safrando.presentation.statements.mapper

import com.nandoligeiro.safrando.domain.statements.model.BankStatementDomain
import com.nandoligeiro.safrando.presentation.statements.model.UiStatementModel

class BankStatementDomainToUiMapper {

    fun toUiBankStatement(statements: List<BankStatementDomain>) =
        statements.map { statement ->
            UiStatementModel(
                nameStatement = statement.nameStatement,
                description = statement.description,
                amount = statement.amount,
                statementDate = statement.statementDate
            )
        }
}
