package com.example.testeandroideveris.feature.statements.domain.usecases

import com.example.testeandroideveris.feature.statements.domain.repository.StatementRepository

class StatementUseCase(private val repository: StatementRepository) {

    suspend fun getStatements(userId: Int) = repository.getStatements(userId)
}