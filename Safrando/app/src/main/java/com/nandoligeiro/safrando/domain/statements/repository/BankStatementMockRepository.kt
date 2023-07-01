package com.nandoligeiro.safrando.domain.statements.repository

import com.nandoligeiro.safrando.domain.statements.model.BankStatementDomain
import javax.inject.Inject

class BankStatementMockRepository @Inject constructor() : BankStatementRepository {
    override suspend fun getBankStatement(userId: Int) = listOf(
        BankStatementDomain(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        ),
        BankStatementDomain(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        ),
        BankStatementDomain(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        ),
        BankStatementDomain(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        ),
        BankStatementDomain(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        ),BankStatementDomain(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        ),BankStatementDomain(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        ),BankStatementDomain(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        ),BankStatementDomain(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        ),BankStatementDomain(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        ),
        BankStatementDomain(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        )
    )
}
