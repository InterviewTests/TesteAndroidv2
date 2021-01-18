package com.jeanjnap.bankapp.ui.login

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.intent.Intents
import com.jeanjnap.bankapp.BaseTest
import com.jeanjnap.bankapp.ui.statements.StatementsViewModel
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

class LoginActivityTest : BaseTest() {

    @MockK
    private lateinit var bankUseCase: BankUseCase

    private lateinit var activityScenario: ActivityScenario<LoginActivity>

    private lateinit var activity: LoginActivity

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        viewModel = LoginViewModel(network, bankUseCase)

        loadKoinModules(module {
            viewModel { viewModel }
            viewModel { StatementsViewModel(network, bankUseCase) }
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
        every { bankUseCase.getUser() } returns null
        startActivity()

        loginActivityRobot {
            elementsMustBeConfiguredCorrectly()
        }
    }

    @Test
    fun onCreate_withSavedUsername_shouldSetInUserField() {
        val user = "user@test.com"
        every { bankUseCase.getUser() } returns user
        startActivity()

        loginActivityRobot {
            verifyUsername(user)
        }
    }

    @Test
    fun invalidFields() {
        every { bankUseCase.getUser() } returns null
        startActivity()

        loginActivityRobot {
            typeUser(false)
            typePass(false)
            hasUserErrorMessage("Usuário inválido")
            hasPassErrorMessage("Senha inválida")
        }
    }

    @Test
    fun validFields() {
        every { bankUseCase.getUser() } returns null
        startActivity()

        loginActivityRobot {
            typeUser(true)
            typePass(true)
            hasUserErrorMessage(null)
            hasPassErrorMessage(null)
        }
    }

    @Test
    fun loginSuccess() {
        every { bankUseCase.getUser() } returns null
        coEvery { bankUseCase.login(any(), any()) } returns SuccessResponse(mockUserAccount())
        coEvery { bankUseCase.getStatements(any()) } returns SuccessResponse(mockStatementList())
        startActivity()

        loginActivityRobot {
            typeUser(true)
            typePass(true)
            verifyStatementsScreen()
        }
    }

    private fun mockUserAccount() = UserAccount(
        userId = 1L,
        name = "Jose da Silva Teste",
        bankAccount = "1234",
        agency = "123456",
        balance = BigDecimal.TEN
    )

    private fun mockStatementList() = listOf(
        mockStatement(),
        mockStatement(),
        mockStatement(),
        mockStatement(),
        mockStatement()
    )

    private fun mockStatement() =  Statement(
        title = "Pagamento",
        desc = "Conta de luz",
        date = Date(1610852400000),
        value = BigDecimal.TEN
    )

    private fun startActivity() {
        activityScenario = ActivityScenario.launch(LoginActivity::class.java).onActivity {
            activity = it
        }
    }
}