package com.nandoligeiro.safrando.datasource.bankstatement

import com.nandoligeiro.safrando.data.api.SafrandoService
import com.nandoligeiro.safrando.data.bankstatement.datasource.BankStatementDataSource
import com.nandoligeiro.safrando.datasource.bankstatement.mapper.BankStatementEntityToDataMapper
import javax.inject.Inject

class BankStatementDataSourceImpl @Inject constructor(
    private val api: SafrandoService,
    private val toDataMapper: BankStatementEntityToDataMapper
) : BankStatementDataSource {
    override suspend fun getBankStatement(
        userId: Int
    ) = toDataMapper.toData(api.getBankStatements(userId))
}