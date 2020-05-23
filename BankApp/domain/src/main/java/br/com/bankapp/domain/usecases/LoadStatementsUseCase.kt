package br.com.bankapp.domain.usecases

import br.com.bankapp.domain.repository.StatementRepository
import javax.inject.Inject

class LoadStatementsUseCase @Inject constructor(
    private val statementRepository: StatementRepository
) {
    suspend operator fun invoke(userId: Int) =
        statementRepository.loadStatements(userId)
}