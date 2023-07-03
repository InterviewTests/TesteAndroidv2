package com.nandoligeiro.safrando.domain.statements.repository

import com.nandoligeiro.safrando.domain.statements.model.BankStatementDomain

interface BankStatementRepository {
    suspend fun getBankStatement(userId: Int): List<BankStatementDomain>
}
