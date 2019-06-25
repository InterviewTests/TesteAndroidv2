package com.farage.testeandroidv2.domain

import com.farage.testeandroidv2.domain.model.StatementsModel
import com.farage.testeandroidv2.util.ResultState

interface StatementRepository {

    suspend fun getAllStatements(id: Int): ResultState<List<StatementsModel>>

}