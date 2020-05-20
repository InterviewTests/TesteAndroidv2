package br.com.bankapp.domain.usecases

import br.com.bankapp.domain.repository.StatementRepository
import javax.inject.Inject

class GetStatementsUseCase @Inject constructor(
    private val statementRepository: StatementRepository
) {
    operator fun invoke(userId: Int) =
        statementRepository.getStatements(userId)
}