package com.lucianogiardino.bankapp

import com.lucianogiardino.bankapp.di.appModule
import com.lucianogiardino.bankapp.domain.model.Statement
import com.lucianogiardino.bankapp.domain.usecase.FetchStatementUseCase
import com.lucianogiardino.bankapp.ui.statement.StatementContract
import com.lucianogiardino.bankapp.ui.statement.StatementPresenter
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
class StatementPresenterTest {
    @Mock
    lateinit var view: StatementContract.View

    @Mock
    lateinit var fetchStatementUseCase: StatementContract.UseCase.FetchStatement

    private lateinit var presenter: StatementPresenter

    @Before
    fun setup() {
        startKoin { modules(appModule) }
        MockitoAnnotations.initMocks(this)
        presenter = StatementPresenter(view, fetchStatementUseCase)
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun fetchStatementSuccessTest() {
        val captor = com.nhaarman.mockitokotlin2.argumentCaptor<StatementContract.Presenter.OnFetchStatementResponse>()

        presenter.fetchStatement()
        Mockito.verify(fetchStatementUseCase).execute(captor.capture())

        captor.firstValue.onFetchStatementResponseSuccess(Mockito.anyList<Statement>() as ArrayList<Statement>)
        Mockito.verify(view).setupStatementList(Mockito.anyList<Statement>() as ArrayList<Statement>)

    }

    @Test
    fun fetchStatementFailedTest() {
        val captor = com.nhaarman.mockitokotlin2.argumentCaptor<StatementContract.Presenter.OnFetchStatementResponse>()

        presenter.fetchStatement()
        Mockito.verify(fetchStatementUseCase).execute(captor.capture())

        captor.firstValue.onFetchStatementResponseFailed("Falha ao tentar acessar o servidor")
        Mockito.verify(view).showError("Falha ao tentar acessar o servidor")

    }

}