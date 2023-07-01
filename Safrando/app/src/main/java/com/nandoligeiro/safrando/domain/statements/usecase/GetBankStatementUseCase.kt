package com.nandoligeiro.safrando.domain.statements.usecase

import com.nandoligeiro.safrando.domain.result.DomainResult
import com.nandoligeiro.safrando.domain.statements.model.BankStatementDomain

interface GetBankStatementUseCase {
    suspend operator fun invoke(userId: Int): DomainResult<List<BankStatementDomain>>
}