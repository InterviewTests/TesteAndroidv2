package br.com.crmm.bankapplication.domain.usecase

import br.com.crmm.bankapplication.domain.repository.StatementRepository

class StatementUseCase(
    private val statementRepository: StatementRepository
) {

    fun execute(userId: String) = statementRepository.fetch(userId)
}