package com.example.testeandroideveris.feature.statements.domain.repository

import com.example.testeandroideveris.feature.statements.data.StatementList
import kotlinx.coroutines.flow.Flow

interface StatementRepository {

    suspend fun getStatements(userId: Int): Flow<StatementList>
}