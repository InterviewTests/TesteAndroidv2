package com.example.testeandroideveris.feature.statements.data.repository

import com.example.testeandroideveris.data.api.RetrofitBuilder
import com.example.testeandroideveris.feature.statements.domain.repository.StatementRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class StatementRepositoryImpl(): StatementRepository {
    override suspend fun getStatements(userId: Int) = flow {
            emit( RetrofitBuilder.apiStatement.getStatements(userId))
        }.flowOn(Dispatchers.IO)
}