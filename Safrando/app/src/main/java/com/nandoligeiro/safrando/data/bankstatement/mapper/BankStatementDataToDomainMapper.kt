package com.nandoligeiro.safrando.data.bankstatement.mapper

import com.nandoligeiro.safrando.data.bankstatement.model.BankStatementData
import com.nandoligeiro.safrando.domain.statements.model.BankStatementDomain

class BankStatementDataToDomainMapper {

    fun toDomainBankStatement(statements: List<BankStatementData>) =
        statements.map { statement ->
            BankStatementDomain(
                nameStatement = statement.nameStatement,
                description = statement.description,
                amount = statement.amount,
                statementDate = statement.statementDate
            )
        }
}
