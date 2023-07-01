package com.nandoligeiro.safrando.datasource.bankstatement.mapper

import com.nandoligeiro.safrando.data.bankstatement.model.BankStatementData
import com.nandoligeiro.safrando.datasource.bankstatement.entity.BankStatementEntity

class BankStatementEntityToDataMapper {

    fun toData(statements: List<BankStatementEntity>) =
        statements.map { statement ->
            BankStatementData(
                nameStatement = statement.nameStatement,
                description = statement.description,
                amount = statement.amount,
                statementDate = statement.statementDate
            )
        }
}
