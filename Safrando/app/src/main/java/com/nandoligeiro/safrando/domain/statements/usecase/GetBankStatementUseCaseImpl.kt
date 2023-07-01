package com.nandoligeiro.safrando.domain.statements.usecase

import com.nandoligeiro.safrando.di.IoDispatcher
import com.nandoligeiro.safrando.domain.common.currencyFormatter.CurrencyFormatterUseCase
import com.nandoligeiro.safrando.domain.result.DomainResult
import com.nandoligeiro.safrando.domain.statements.model.BankStatementDomain
import com.nandoligeiro.safrando.domain.statements.repository.BankStatementRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBankStatementUseCaseImpl @Inject constructor(
    private val bankStatementRepository: BankStatementRepository,
    private val currencyFormatterUseCase: CurrencyFormatterUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GetBankStatementUseCase {

    override suspend fun invoke(userId: Int):
            DomainResult<List<BankStatementDomain>> = withContext(ioDispatcher) {

        try {
            val statement = bankStatementRepository.getBankStatement(userId)
            DomainResult.Success(
                statement.map {
                    it.copy(amount = currencyFormatterUseCase(it.amount))
                }
            )
        } catch (e: Exception) {
            DomainResult.Error(e)
        }
    }
}
