package br.com.silas.testeandroidv2.br.com.silas.testeandroidv2.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.silas.domain.statements.StatementsInteractor
import br.com.silas.testeandroidv2.any
import br.com.silas.testeandroidv2.br.com.silas.testeandroidv2.mocks.ErrorResponseMock
import br.com.silas.testeandroidv2.br.com.silas.testeandroidv2.mocks.StatementsMock
import br.com.silas.testeandroidv2.ui.statements.StatementsViewModel
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StatementsViewModelTest {

    @Mock
    lateinit var statementsInteractor: StatementsInteractor

    lateinit var statementsViewModel: StatementsViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        statementsViewModel = StatementsViewModel(statementsInteractor)
    }

    @Test
    fun `When fetch statements is successful should be return a list of statements`() {
        val statementsList = StatementsMock.getListStatements()
        val statementsError = ErrorResponseMock.getErrorResponseIsEmpty()
        val pairResponse = Pair(statementsError, statementsList)

        `when`(statementsInteractor.execute(any())).thenReturn(Single.just(pairResponse))
        statementsViewModel.loadStatements(152)

        assertThat(statementsViewModel.result.value, `is`(pairResponse.second))
    }

    @Test
    fun `When fetch statements is unsuccessful should be return an error response`() {
        val statementsList = StatementsMock.getListStatements()
        val statementsError = ErrorResponseMock.getErrorResponseIsNotEmpty()
        val pairResponse = Pair(statementsError, statementsList)

        `when`(statementsInteractor.execute(any())).thenReturn(Single.just(pairResponse))
        statementsViewModel.loadStatements(152)

        assertThat(statementsViewModel.errorStatements.value, `is`(pairResponse.first))
    }
}