package com.farage.testeandroidv2.repository

import com.farage.testeandroidv2.datasource.StatementDataSource
import com.farage.testeandroidv2.datasource.model.StatementRequest
import com.farage.testeandroidv2.util.ResultState
import com.farage.testeandroidv2.util.ResultType
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class StatementRepositoryImplTest {

    private val statementDataSource: StatementDataSource = mock()
    private val statementRepositoryImpl = StatementRepositoryImpl(statementDataSource)

    @Test
    fun getAllStatements_ShouldReturnResult(){
        runBlocking {
            whenever(statementDataSource.getAllStatements(1)).thenReturn(
                ResultState.success(StatementRequest(mutableListOf()))
            )
            val result = statementRepositoryImpl.getAllStatements(1)
            assertEquals(ResultType.SUCCESS, result.resultType)
        }
    }


}