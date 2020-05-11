package com.lucianogiardino.bankapp

import com.lucianogiardino.bankapp.di.appModule
import com.lucianogiardino.bankapp.domain.usecase.FetchStatementUseCase
import com.lucianogiardino.bankapp.domain.usecase.LoginUseCase
import com.lucianogiardino.bankapp.presentation.login.LoginContract
import com.lucianogiardino.bankapp.presentation.statement.StatementContract
import com.nhaarman.mockitokotlin2.eq
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
class LoginUseCaseTest {
    @Mock
    lateinit var repository: LoginContract.Repository

    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setup() {
        startKoin { modules(appModule) }
        MockitoAnnotations.initMocks(this)
        loginUseCase = LoginUseCase(repository)

    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun executeSuccessTest() {
        repository.login(loginUseCase,"luciano.giardino@ibm.com","Teste@123")
        Mockito.verify(repository).login(loginUseCase,"luciano.giardino@ibm.com","Teste@123")

    }
}