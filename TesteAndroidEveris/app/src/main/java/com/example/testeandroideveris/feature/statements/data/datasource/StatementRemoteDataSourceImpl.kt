package com.example.testeandroideveris.feature.statements.data.datasource

import com.example.testeandroideveris.data.api.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class StatementRemoteDataSourceImpl : StatementRemoteDataSource {
    override suspend fun getStatements(userId: Int) = flow {
        emit( RetrofitBuilder.apiStatement.getStatements(userId))
    }.flowOn(Dispatchers.IO)
}