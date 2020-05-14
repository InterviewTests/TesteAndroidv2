package com.example.testeandroideveris.feature.statements.data.datasource

import com.example.testeandroideveris.feature.statements.data.api.StatementAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class StatementRemoteDataSourceImpl(private val api: StatementAPI) : StatementRemoteDataSource {
    override suspend fun getStatements(userId: Int) = flow {
        emit(api.getStatements(userId))
    }.flowOn(Dispatchers.IO)
}