package pt.felipegouveia.bankapp.presentation.statements

import android.view.View
import androidx.lifecycle.Observer
import io.reactivex.Single
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import pt.felipegouveia.bankapp.Util
import pt.felipegouveia.bankapp.domain.StatementsRepository
import pt.felipegouveia.bankapp.domain.interactors.StatementsUseCase
import pt.felipegouveia.bankapp.domain.model.statements.Statements
import pt.felipegouveia.bankapp.presentation.TrampolineSchedulerProvider
import pt.felipegouveia.bankapp.presentation.entity.Response
import pt.felipegouveia.bankapp.presentation.entity.Status
import pt.felipegouveia.bankapp.presentation.statements.entity.StatementsPresentation
import pt.felipegouveia.bankapp.presentation.statements.entity.mapper.StatementsPresentationMapper

@RunWith(JUnit4::class)
class StatementsViewModelTest {

    private val repository = mock(StatementsRepository::class.java)
    @InjectMocks
    private lateinit var useCase: StatementsUseCase
    private val schedulerProvider = TrampolineSchedulerProvider()
    private val mapper = StatementsPresentationMapper()

    private var statementsViewModel = StatementsViewModel(schedulerProvider, useCase, mapper)

    @Mock
    private lateinit var statementsObserver: Observer<Response<StatementsPresentation>>
    @Mock
    private lateinit var progressBarObserver: Observer<Int>

    private lateinit var successDomainResponse: Statements
    private lateinit var errorDomainResponse: Statements

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        successDomainResponse = Util.createStatementsDomainMockSingle("api-response/statements_response.json")
        errorDomainResponse = Util.createStatementsDomainMockSingle("api-response/login_error_response.json")
        statementsViewModel.mutableProgressbar.observeForever(progressBarObserver)
    }

    @Test
    fun testNull() {
        assertThat(statementsViewModel.statements, notNullValue())
        verify(useCase, never()).getStatements(anyInt())
    }

    @Test
    fun dontFetchWithoutObservers() {
        statementsViewModel.setUserId(1)
        verify(useCase, never()).getStatements(anyInt())
    }

    @Test
    fun fetchWhenObserved() {
        statementsViewModel.setUserId(1)
        statementsViewModel.statements.observeForever(statementsObserver)
        verify(useCase).getStatements(1)
    }

    @Test
    fun retry() {
        statementsViewModel.retry()
        verifyNoMoreInteractions(useCase)
        statementsViewModel.setUserId(1)
        verifyNoMoreInteractions(useCase)
        statementsViewModel.statements.observeForever(statementsObserver)
        verify(repository).getStatements(1)
        reset(repository)
        statementsViewModel.retry()
        verify(repository).getStatements(1)
    }

    @Test
    fun fetchStatements() {
        // given
        statementsViewModel.setUserId(1)
        BDDMockito.given(useCase.getStatements(1)).willReturn(Single.just(successDomainResponse))

        // when
        statementsViewModel.statements.observeForever(statementsObserver)

        // then
        // Expected data response
        val expected = Response(status = Status.SUCCESSFUL, data = mapper.mapFrom(successDomainResponse))

        BDDMockito.then(progressBarObserver).should().onChanged(View.VISIBLE)
        BDDMockito.then(progressBarObserver).should().onChanged(View.GONE)
        BDDMockito.then(statementsObserver).should().onChanged(expected)
    }

}