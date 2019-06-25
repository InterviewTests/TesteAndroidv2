package com.farage.testeandroidv2.domain.usecase

import com.farage.testeandroidv2.domain.StatementRepository
import com.farage.testeandroidv2.util.ResultState
import com.farage.testeandroidv2.util.ResultType
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test


class StatementsUseCaseTest {

    private val mockStatementRepository: StatementRepository = mock()
    private val statementsUseCase = StatementsUseCase(mockStatementRepository)

    @Test
    fun getAllStatements_ShouldReturnResult() {
        runBlocking {
            whenever(mockStatementRepository.getAllStatements(1)).thenReturn(
                ResultState.success(mutableListOf())
            )
            val result = statementsUseCase.execute(1)
            assertEquals(ResultType.SUCCESS, result.resultType)
        }
    }

}