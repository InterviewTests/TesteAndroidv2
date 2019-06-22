package com.farage.testeandroidv2.domain.usecase

import com.farage.testeandroidv2.domain.StatementRepository
import com.farage.testeandroidv2.domain.model.StatementsModel
import com.farage.testeandroidv2.util.ResultState

class StatementsUseCase(private val statementRepository: StatementRepository) {

    suspend fun execute(id: Int): ResultState<List<StatementsModel>> = statementRepository.getAllStatements(id)

}