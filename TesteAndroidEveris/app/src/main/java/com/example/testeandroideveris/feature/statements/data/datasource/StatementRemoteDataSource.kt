package com.example.testeandroideveris.feature.statements.data.datasource

import com.example.testeandroideveris.feature.statements.data.StatementList
import kotlinx.coroutines.flow.Flow

interface StatementRemoteDataSource {
    suspend fun getStatements(userId: Int): Flow<StatementList>
}