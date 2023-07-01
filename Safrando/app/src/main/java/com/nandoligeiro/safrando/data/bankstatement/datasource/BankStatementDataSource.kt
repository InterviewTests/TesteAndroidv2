package com.nandoligeiro.safrando.data.bankstatement.datasource

import com.nandoligeiro.safrando.data.bankstatement.model.BankStatementData

interface BankStatementDataSource {
    suspend fun getBankStatement(userId: Int): List<BankStatementData>
}