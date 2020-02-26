package com.br.myapplication.usercase

import com.br.myapplication.model.Statement
import com.br.myapplication.repository.HomeRepository
import com.br.myapplication.service.ApiResult

class HomeUserCase(private val homeRepository: HomeRepository) : BaseUserCase<List<Statement>, HomeUserCase.Params>() {

    override suspend fun run(param: Params): ApiResult<List<Statement>> = homeRepository.getStatements(param.userID)

    data class Params(val userID: String)
}