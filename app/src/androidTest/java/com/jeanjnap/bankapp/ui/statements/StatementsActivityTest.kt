package com.jeanjnap.bankapp.ui.statements

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.intent.Intents
import com.jeanjnap.bankapp.BaseTest
import com.jeanjnap.bankapp.ui.login.LoginViewModel
import com.jeanjnap.domain.entity.Statement
import com.jeanjnap.domain.entity.SuccessResponse
import com.jeanjnap.domain.entity.UserAccount
import com.jeanjnap.domain.usecase.BankUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import java.math.BigDecimal
import java.util.Date

class StatementsActivityTest : BaseTest() {

    @MockK
    private lateinit var bankUseCase: BankUseCase

    private lateinit var activityScenario: ActivityScenario<StatementsActivity>

    private lateinit var activity: StatementsActivity

    private lateinit var viewModel: StatementsViewModel

    @Before
    fun setup() {
        viewModel = StatementsViewModel(network, bankUseCase)

        loadKoinModules(module {
            viewModel { viewModel }
            viewModel { LoginViewModel(network, bankUseCase) }
        })
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
        activityScenario.close()
    }

    @Test
    fun onCreate() {
        coEvery { bankUseCase.getStatements(any()) } returns SuccessResponse(mockStatementList())
        startActivity()

        statementsActivityRobot {
            elementsMustBeConfiguredCorrectly()
        }
    }

    @Test
    fun logoutClick() {
        every { bankUseCase.getUser() } returns "user@test.com"
        coEvery { bankUseCase.getStatements(any()) } returns SuccessResponse(mockStatementList())
        startActivity()

        statementsActivityRobot {
            logout()
            verifyLoginScreen()
        }
    }

    private fun mockUserAccount() = UserAccount(
        userId = 1L,
        name = "Jose da Silva Teste",
        bankAccount = "2050",
        agency = "011112224",
        balance = BigDecimal.TEN
    )

    private fun mockStatementList() = listOf(
        mockStatement(),
        mockStatement(),
        mockStatement(),
        mockStatement(),
        mockStatement()
    )

    private fun mockStatement() = Statement(
        title = "Pagamento",
        desc = "Conta de luz",
        date = Date(1610852400000),
        value = BigDecimal.TEN
    )

    private fun startActivity() {
        activityScenario = ActivityScenario.launch(
            Intent(getApplicationContext(),StatementsActivity::class.java).apply {
                putExtra(USER_ACCOUNT_EXTRA, mockUserAccount())
            }
        )

        activityScenario.onActivity {
            activity = it
        }
    }

    companion object {
        private const val USER_ACCOUNT_EXTRA = "userAccount"
    }
}