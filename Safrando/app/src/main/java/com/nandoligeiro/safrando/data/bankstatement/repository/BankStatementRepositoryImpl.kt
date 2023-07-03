package com.nandoligeiro.safrando.data.bankstatement.repository

import com.nandoligeiro.safrando.data.bankstatement.datasource.BankStatementDataSource
import com.nandoligeiro.safrando.data.bankstatement.mapper.BankStatementDataToDomainMapper
import com.nandoligeiro.safrando.domain.statements.repository.BankStatementRepository
import javax.inject.Inject

class BankStatementRepositoryImpl @Inject constructor(
    private val dataSource: BankStatementDataSource,
    private val toDomainMapper: BankStatementDataToDomainMapper
) : BankStatementRepository {
    override suspend fun getBankStatement(
        userId: Int
    ) = toDomainMapper.toDomainBankStatement(dataSource.getBankStatement(userId))

}