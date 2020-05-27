package com.br.myapplication.usecase

import com.br.myapplication.model.ResponseStatement
import com.br.myapplication.repository.HomeRepository

class HomeUseCase(private val homeRepository: HomeRepository) : BaseUseCase<ResponseStatement, HomeUseCase.Params>() {

    override suspend fun run(param: Params): ResponseStatement = homeRepository.getStatements(param.userID)

    data class Params(val userID: String)
}