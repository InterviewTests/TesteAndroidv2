package pt.felipegouveia.bankapp.presentation.statements

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.reactivex.Flowable
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import pt.felipegouveia.bankapp.Util
import pt.felipegouveia.bankapp.domain.StatementsRepository
import pt.felipegouveia.bankapp.domain.interactors.StatementsUseCase
import pt.felipegouveia.bankapp.domain.model.statements.Statements
import pt.felipegouveia.bankapp.presentation.BaseSchedulerProvider
import pt.felipegouveia.bankapp.presentation.TrampolineSchedulerProvider
import pt.felipegouveia.bankapp.presentation.entity.Response
import pt.felipegouveia.bankapp.presentation.entity.Status
import pt.felipegouveia.bankapp.presentation.login.entity.UserAccountPresentation
import pt.felipegouveia.bankapp.presentation.statements.entity.StatementsPresentation
import pt.felipegouveia.bankapp.presentation.statements.entity.mapper.StatementsPresentationMapper

@RunWith(RobolectricTestRunner::class)
class StatementsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: StatementsRepository

    @Mock
    private lateinit var useCase: StatementsUseCase
    private lateinit var schedulerProvider: BaseSchedulerProvider
    private val mapper = StatementsPresentationMapper()
    private lateinit var statementsViewModel: StatementsViewModel

    @Mock
    private lateinit var statementsObserver: Observer<Response<StatementsPresentation>>
    @Mock
    private lateinit var progressBarObserver: Observer<Int>

    private lateinit var successDomainResponse: Statements
    private lateinit var errorDomainResponse: Statements

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        schedulerProvider = TrampolineSchedulerProvider()
        statementsViewModel = StatementsViewModel(schedulerProvider, useCase, mapper)
        successDomainResponse = Util.createStatementsDomainMockSingle("api-response/statements_response.json")
        errorDomainResponse = Util.createStatementsDomainMockSingle("api-response/statements_error_response.json")
        statementsViewModel.mutableProgressbar.observeForever(progressBarObserver)
    }

    @Test
    fun testNull() {
        assertThat(statementsViewModel.statements, notNullValue())
        verify(useCase, never()).getStatements(anyInt())
    }

    @Test
    fun dontFetchWithoutObservers() {
        val userAccount = UserAccountPresentation(1)
        statementsViewModel.setUserAccount(userAccount)
        verify(useCase, never()).getStatements(anyInt())
    }

    @Test
    fun fetchWhenObserved() {
        `when`(useCase.getStatements(1)).thenReturn(Flowable.just(successDomainResponse))
        val userAccount = UserAccountPresentation(1)
        statementsViewModel.setUserAccount(userAccount)
        statementsViewModel.statements.observeForever(statementsObserver)
        verify(useCase).getStatements(1)
    }

    @Test
    fun retry() {
        statementsViewModel.retry()
        verifyNoMoreInteractions(useCase)
        val userAccount = UserAccountPresentation(1)
        statementsViewModel.setUserAccount(userAccount)
        verifyNoMoreInteractions(useCase)
        `when`(useCase.getStatements(1)).thenReturn(Flowable.just(successDomainResponse))
        statementsViewModel.statements.observeForever(statementsObserver)
        verify(useCase).getStatements(1)
        reset(useCase)
        `when`(useCase.getStatements(1)).thenReturn(Flowable.just(successDomainResponse))
        statementsViewModel.retry()
        verify(useCase).getStatements(1)
    }

    @Test
    fun fetchStatements() {
        // given
        `when`(useCase.getStatements(1)).thenReturn(Flowable.just(successDomainResponse))
        val userAccount = UserAccountPresentation(1)
        statementsViewModel.setUserAccount(userAccount)

        // when
        statementsViewModel.statements.observeForever(statementsObserver)

        // then
        // Expected data response
        val expected = Response(status = Status.SUCCESSFUL, data = mapper.mapFrom(successDomainResponse))

        then(progressBarObserver).should().onChanged(View.VISIBLE)
        then(progressBarObserver).should().onChanged(View.GONE)
        then(statementsObserver).should().onChanged(expected)
    }

    @Test
    fun fetchStatementsWithUserNotFound() {
        `when`(useCase.getStatements(1000)).thenReturn(Flowable.just(errorDomainResponse))
        val userAccount = UserAccountPresentation(1000)
        statementsViewModel.setUserAccount(userAccount)
        statementsViewModel.statements.observeForever(statementsObserver)
        val expected = Response(status = Status.SUCCESSFUL, data = mapper.mapFrom(errorDomainResponse))
        then(progressBarObserver).should().onChanged(View.VISIBLE)
        then(progressBarObserver).should().onChanged(View.GONE)
        then(statementsObserver).should().onChanged(expected)
    }

}