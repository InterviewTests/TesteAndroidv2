package com.nandoligeiro.safrando.datasource.bankstatement.local

import com.nandoligeiro.safrando.data.api.SafrandoService
import com.nandoligeiro.safrando.data.bankstatement.datasource.BankStatementDataSource
import com.nandoligeiro.safrando.datasource.bankstatement.entity.BankStatementEntity
import com.nandoligeiro.safrando.datasource.bankstatement.mapper.BankStatementEntityToDataMapper
import javax.inject.Inject

class LocalBankStatementDataSourceImpl @Inject constructor(
    private val api: SafrandoService,
    private val toDataMapper: BankStatementEntityToDataMapper
) : BankStatementDataSource {
    override suspend fun getBankStatement(
        userId: Int
    ) = toDataMapper.toData(mockBankStatement()) //(api.getBankStatements(userId))
    private fun mockBankStatement() = listOf(
        BankStatementEntity(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        ),
        BankStatementEntity(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        ),
        BankStatementEntity(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        ),
        BankStatementEntity(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        ),
        BankStatementEntity(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        ), BankStatementEntity(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        ), BankStatementEntity(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        ), BankStatementEntity(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        ), BankStatementEntity(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        ), BankStatementEntity(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        ),
        BankStatementEntity(
            nameStatement = "Pagamento",
            description = "Conta de Luz",
            amount = "1000",
            statementDate = "12/12/2023"
        )
    )
}