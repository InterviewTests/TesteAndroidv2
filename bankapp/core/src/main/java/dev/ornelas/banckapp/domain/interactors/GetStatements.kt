package dev.ornelas.banckapp.domain.interactors

import dev.ornelas.banckapp.domain.repository.StatementRepository

class GetStatements(private val statementRepository: StatementRepository) {

    operator suspend fun invoke(idUser: Int) = statementRepository.GetStatements(idUser)


}