package com.br.myapplication.domain.usecase

import com.br.myapplication.data.model.ResponseStatement
import com.br.myapplication.data.repository.HomeRepository

class HomeUseCase(private val homeRepository: HomeRepository) : BaseUseCase<ResponseStatement, HomeUseCase.Params>() {

    override suspend fun run(param: Params): ResponseStatement = homeRepository.getStatements(param.userID)

    data class Params(val userID: String)
}