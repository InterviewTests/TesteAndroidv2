package br.com.alex.bankappchallenge.features.statement

import br.com.alex.bankappchallenge.di.androidModule
import br.com.alex.bankappchallenge.di.interactorModule
import br.com.alex.bankappchallenge.di.mapperModule
import br.com.alex.bankappchallenge.di.networkModule
import br.com.alex.bankappchallenge.di.reducerModule
import br.com.alex.bankappchallenge.di.repositoryModule
import br.com.alex.bankappchallenge.di.viewModelModule
import br.com.alex.bankappchallenge.feature.statement.StatementIntentions
import br.com.alex.bankappchallenge.feature.statement.StatementNavigation
import br.com.alex.bankappchallenge.feature.statement.StatementState
import br.com.alex.bankappchallenge.feature.statement.StatementViewModel
import br.com.alex.bankappchallenge.feature.statement.UserAccountState
import br.com.alex.bankappchallenge.interactor.mapper.StatementMapperContract
import br.com.alex.bankappchallenge.model.FormatedUserAccount
import br.com.alex.bankappchallenge.network.model.StatementResponse
import br.com.alex.bankappchallenge.network.model.UserAccount
import br.com.alex.bankappchallenge.repository.LoginRepositoryContract
import br.com.alex.bankappchallenge.rules.LocalTestRule
import br.com.alex.bankappchallenge.rules.RxLocalRule
import com.squareup.moshi.Moshi
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given

class StatementViewModelTest : KoinTest {
    private val statementViewModel: StatementViewModel by inject()
    private val statementMapperContract: StatementMapperContract by inject()

    @get:Rule
    val rxRule = RxLocalRule()

    @get:Rule
    val localTestRule = LocalTestRule(
        mutableListOf(
            networkModule,
            viewModelModule,
            androidModule,
            interactorModule,
            reducerModule,
            repositoryModule,
            mapperModule
        )
    )

    @Before
    fun setupUserAccount() {
        declareMock<LoginRepositoryContract> {
            given(this.getUserLogin())
                .willReturn("user@gmail.com")

            given(this.getUserAccount())
                .willReturn(
                    UserAccount(
                        1,
                        "Jose da Silva Teste",
                        "2050",
                        "012314564",
                        3.3445)
                )
        }
    }

    @Test
    fun shouldEmmitNavigateBackToLogin_whenTriggerLogoutIntention() {
        setupStatementSuccess()

        val expectedNavigation = StatementNavigation.NavigateBackToLogin
        var actualNavigation: StatementNavigation? = null

        statementViewModel.navigations.observeForever { event ->
            event.getContentIfNotHandled()?.let { navigation ->
                actualNavigation = navigation
            }
        }

        statementViewModel.execute(StatementIntentions.Logout)
        assertEquals(expectedNavigation, actualNavigation)
    }

    @Test
    fun shouldEmmitErrorState_whenStatementFailure() {
        setupStatementFailure()

        val expectedState = StatementState(
            isLoadError = true,
            isLoadingStatement = false,
            statementList = listOf(),
            errorMessage = "Usuário não encontrado"
        )

        var actualState = StatementState()

        statementViewModel.statesStatement.observeForever { state ->
            state.let {
                actualState = it
            }
        }

        statementViewModel.execute(StatementIntentions.FetchStatements)
        assertEquals(expectedState, actualState)
    }

    @Test
    fun shouldEmmitStatementListState_whenStatementIsLoadedSuccess() {
        setupStatementSuccess()

        val statementResponse = Moshi.Builder().build().adapter(StatementResponse::class.java).fromJson(STATEMENT_SUCCESS)

        val expectedState = StatementState(
            isLoadingStatement = false,
            isLoadError = false,
            statementList = statementMapperContract.mapper(statementResponse!!.statementList),
            errorMessage = ""
        )

        var actualState = StatementState()

        statementViewModel.statesStatement.observeForever { state ->
            state.let {
                actualState = it
            }
        }

        statementViewModel.execute(StatementIntentions.FetchStatements)
        assertEquals(expectedState, actualState)
    }

    @Test
    fun shouldEmmitUserAccountData_whenUserAccountIsLoaded() {
        setupStatementSuccess()

        val expectedState = UserAccountState(
            isLoadingUserAccount = false,
            userAccount = FormatedUserAccount(
                "Jose da Silva Teste",
                "2050 / 01.231456-4",
                "R$ 3,34"
            )
        )

        var actualState = UserAccountState()

        statementViewModel.stateUserAccount.observeForever { state ->
            state.let {
                actualState = it
            }
        }

        statementViewModel.execute(StatementIntentions.FetchUserAccountData)
        assertEquals(expectedState, actualState)
    }

    private fun setupStatementSuccess() {
        localTestRule.serverRule.enqueue(MockResponse().setBody(STATEMENT_SUCCESS))
    }

    private fun setupStatementFailure() {
        localTestRule.serverRule.enqueue(MockResponse().setBody(STATEMENT_FAILURE))
    }
}