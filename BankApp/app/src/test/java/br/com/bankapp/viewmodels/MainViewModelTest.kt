package br.com.bankapp.viewmodels

import androidx.lifecycle.liveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import asPagedList
import br.com.bankapp.BaseViewModelTest
import br.com.bankapp.commons.Success
import br.com.bankapp.data.utils.convertStringToDate
import br.com.bankapp.domain.models.Statement
import br.com.bankapp.domain.models.UserAccount
import br.com.bankapp.domain.usecases.GetStatementsUseCase
import br.com.bankapp.domain.usecases.GetUserAccountUseCase
import br.com.bankapp.domain.usecases.LoadStatementsUseCase
import br.com.bankapp.ui.main.MainViewModel
import br.com.fortes.appcolaborador.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.collection.IsCollectionWithSize
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class MainViewModelTest : BaseViewModelTest() {

    private var loadStatementsUseCase = mockk<LoadStatementsUseCase>()
    private var statementsUseCase = mockk<GetStatementsUseCase>()
    private var userAccountUseCase = mockk<GetUserAccountUseCase>()
    private lateinit var mainViewModel: MainViewModel
    private lateinit var statementOne: Statement
    private lateinit var statementTwo: Statement
    private lateinit var userAccount: UserAccount

    @Before
    fun setup() {
        mainViewModel = MainViewModel(loadStatementsUseCase, statementsUseCase, userAccountUseCase)
        statementOne = Statement(
            id = 1,
            title = "Pagamento",
            desc = "Conta de luz",
            date = convertStringToDate("2018-08-15"),
            value = -50.0,
            userId = 1
        )

        statementTwo = Statement(
            id = 2,
            title = "TED Recebida",
            desc = "Joao Alfredo",
            date = convertStringToDate("2018-07-25"),
            value = -745.03,
            userId = 1
        )

        userAccount = UserAccount(
            userId = 1,
            name = "User",
            bankAccount = "2050",
            agency = "012314564",
            balance = 3.3445
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun executeLoadStatements_setsUiStatusEvent() {
        runBlockingTest {
            coEvery { loadStatementsUseCase(any()) } returns Unit
            mainViewModel.loadStatements(1, true)
            val uiStatus = mainViewModel.uiState.getOrAwaitValue()
            MatcherAssert.assertThat(uiStatus, CoreMatchers.instanceOf(Success::class.java))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getStatements_confirmObtained() {
        runBlockingTest {
            coEvery { statementsUseCase(any()) } returns liveData {
                emit(listOf(statementOne, statementTwo).asPagedList())
            }
            val statements = mainViewModel.getStatements(1).getOrAwaitValue()

            // Verify the obtained statements
            MatcherAssert.assertThat(statements, CoreMatchers.not(emptyList<Statement>()))
            MatcherAssert.assertThat(statements, IsCollectionWithSize.hasSize(2))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getUserAccount_confirmObtained() {
        runBlockingTest {
            coEvery { userAccountUseCase(any()) } returns liveData {
                emit(userAccount)
            }
            val userAccount = mainViewModel.getUserAccount(1).getOrAwaitValue()

            // Verify the obtained statements
            MatcherAssert.assertThat(userAccount, CoreMatchers.not(CoreMatchers.nullValue()))
            MatcherAssert.assertThat(
                userAccount.userId,
                CoreMatchers.equalTo(1)
            )
        }
    }
}