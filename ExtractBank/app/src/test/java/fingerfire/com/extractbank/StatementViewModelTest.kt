package fingerfire.com.extractbank

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import fingerfire.com.extractbank.features.statements.data.StatementRepository
import fingerfire.com.extractbank.features.statements.data.StatementsResponse
import fingerfire.com.extractbank.features.statements.ui.StatementViewModel
import fingerfire.com.extractbank.features.statements.ui.viewstate.StatementViewState
import fingerfire.com.extractbank.network.ServiceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class StatementViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var statementRepository: StatementRepository

    @Mock
    private lateinit var observer: Observer<StatementViewState>

    private lateinit var statementViewModel: StatementViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        statementViewModel = StatementViewModel(statementRepository)
        statementViewModel.statementLiveData.observeForever(observer)
    }

    @Test
    fun getStatementsForUserSuccess() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        // Given
        val idUser = "123"
        val statements = listOf(
            StatementsResponse(idUser, "2023-01-01", 100.0, "Credit", "Deposit"),
            StatementsResponse(idUser, "2023-01-02", 50.0, "Debit", "Withdrawal")
        )
        `when`(statementRepository.getStatement(idUser)).thenReturn(ServiceState.Success(statements))

        // When
        statementViewModel.getStatementsForUser(idUser)

        // Then
        verify(statementRepository).getStatement(idUser)
        verify(observer).onChanged(StatementViewState.Success(statements))
    }

    @Test
    fun getStatementsForUserEmpty() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        // Given
        val idUser = "123"
        val statements = emptyList<StatementsResponse>()
        `when`(statementRepository.getStatement(idUser)).thenReturn(ServiceState.Success(statements))

        // When
        statementViewModel.getStatementsForUser(idUser)

        // Then
        verify(statementRepository).getStatement(idUser)
        verify(observer).onChanged(StatementViewState.Error("Nenhum extrato encontrado."))
    }

    @Test
    fun getStatementsForUserError() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        val statements = emptyList<StatementsResponse>()
        // Given
        val idUser = "123"
        val errorMessage = "Ocorreu um erro ao obter os extratos."
        `when`(statementRepository.getStatement(idUser)).thenReturn(ServiceState.Error(statements))

        // When
        statementViewModel.getStatementsForUser(idUser)

        // Then
        verify(statementRepository).getStatement(idUser)
        verify(observer).onChanged(StatementViewState.Error(errorMessage))
    }
}