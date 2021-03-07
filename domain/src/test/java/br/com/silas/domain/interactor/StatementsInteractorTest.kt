package br.com.silas.domain.interactor

import br.com.silas.domain.ErrorResponse
import br.com.silas.domain.Schedulers
import br.com.silas.domain.mocks.StatementsMock
import br.com.silas.domain.statements.StatementsInteractor
import br.com.silas.domain.statements.StatementsRepository
import br.com.silas.domain.util.TestScheduler
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StatementsInteractorTest {

    @Mock
    lateinit var statementsRepository: StatementsRepository

    lateinit var scheduler: Schedulers

    lateinit var statementsInteractor: StatementsInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scheduler = TestScheduler()
        statementsInteractor = StatementsInteractor(statementsRepository, scheduler)
    }

    @Test
    fun `When fetch statements is successful should be return a pair of list statements or error statement`() {

        val statementsList = StatementsMock.getListStatements()
        val errorResponse = mock(ErrorResponse::class.java)
        val pairResponse = Pair(errorResponse, statementsList)

        `when`(statementsRepository.fetchStatements(anyInt())).thenReturn(Single.just(pairResponse))
        val result = statementsInteractor.execute(statementsInteractor.Request(152)).test()

        result
            .assertNoErrors()
            .assertValue(pairResponse)
            .assertComplete()

        verify(statementsRepository).fetchStatements(152)
    }

    @Test
    fun `When fetch statements is unsuccessful should be return an exception`() {

        val exception = Exception()

        `when`(statementsRepository.fetchStatements(anyInt())).thenReturn(Single.error(exception))
        val result = statementsInteractor.execute(statementsInteractor.Request(152)).test()

        result
            .assertNoValues()
            .assertError(exception)
            .assertNotComplete()

        verify(statementsRepository).fetchStatements(152)
    }
}