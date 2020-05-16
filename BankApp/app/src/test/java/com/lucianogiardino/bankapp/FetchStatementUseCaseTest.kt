package com.lucianogiardino.bankapp

import com.lucianogiardino.bankapp.di.appModule
import com.lucianogiardino.bankapp.domain.usecase.FetchStatementUseCase
import com.lucianogiardino.bankapp.presentation.statement.StatementContract
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FetchStatementUseCaseTest {

    @Mock
    lateinit var repository: StatementContract.Repository

    private lateinit var fetchStatementUseCase: FetchStatementUseCase

    @Before
    fun setup() {
        startKoin { modules(appModule) }
        MockitoAnnotations.initMocks(this)
        fetchStatementUseCase = FetchStatementUseCase(repository)

    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun executeSuccessTest() {
        repository.fetchStatement(fetchStatementUseCase)
        Mockito.verify(repository).fetchStatement(fetchStatementUseCase)

    }
}