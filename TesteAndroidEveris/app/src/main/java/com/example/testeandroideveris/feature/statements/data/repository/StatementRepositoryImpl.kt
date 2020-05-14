package com.example.testeandroideveris.feature.statements.data.repository

import com.example.testeandroideveris.feature.statements.data.datasource.StatementRemoteDataSource
import com.example.testeandroideveris.feature.statements.domain.repository.StatementRepository

class StatementRepositoryImpl(private val remoteDataSource: StatementRemoteDataSource): StatementRepository {
    override suspend fun getStatements(userId: Int) = remoteDataSource.getStatements(userId)
}