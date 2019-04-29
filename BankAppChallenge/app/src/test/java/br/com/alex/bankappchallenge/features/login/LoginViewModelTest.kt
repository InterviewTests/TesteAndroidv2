package br.com.alex.bankappchallenge.features.login

import br.com.alex.bankappchallenge.di.androidModule
import br.com.alex.bankappchallenge.di.interactorModule
import br.com.alex.bankappchallenge.di.mapperModule
import br.com.alex.bankappchallenge.di.networkModule
import br.com.alex.bankappchallenge.di.reducerModule
import br.com.alex.bankappchallenge.di.repositoryModule
import br.com.alex.bankappchallenge.di.viewModelModule
import br.com.alex.bankappchallenge.feature.login.LoginIntentions
import br.com.alex.bankappchallenge.feature.login.LoginNavigation
import br.com.alex.bankappchallenge.feature.login.LoginState
import br.com.alex.bankappchallenge.feature.login.LoginViewModel
import br.com.alex.bankappchallenge.repository.LoginRepositoryContract
import br.com.alex.bankappchallenge.rules.LocalTestRule
import br.com.alex.bankappchallenge.rules.RxLocalRule
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given

class LoginViewModelTest : KoinTest {

    private val loginViewModel: LoginViewModel by inject()

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
    fun setupRepositoryHawkDependecy() {
        declareMock<LoginRepositoryContract> {
            given(this.getUserLogin()).willReturn("user@gmail.com")
        }
    }

    @Test
    fun shouldEmmitNavigateToStatement_whenLoginIsSuccess() {
        setupLoginSuccess()

        val expectedNavigation = LoginNavigation.NavigateToStatement
        var actualNavigation: LoginNavigation? = null

        loginViewModel.navigations.observeForever { event ->
            event.getContentIfNotHandled()?.let { navigation ->
                actualNavigation = navigation
            }
        }

        loginViewModel.execute(LoginIntentions.Login("user@gmail.com", "T@1"))
        assertEquals(expectedNavigation, actualNavigation)
    }

    @Test
    fun shouldEmmitErrorState_whenLoginFailure() {
        setupLoginFailure()

        val expectedState = LoginState(
            isLoading = false,
            isLoadError = true,
            isPasswordInvalid = false,
            isUserInvalid = false,
            isUserEmpty = false,
            isPasswordEmpty = false,
            errorMessage = "Usuário ou senha incorreta"
        )

        var actualState = LoginState()

        loginViewModel.states.observeForever { state ->
            state.let {
                actualState = it
            }
        }

        loginViewModel.execute(LoginIntentions.Login("user", "T@1"))
        assertEquals(expectedState, actualState)
    }

    @Test
    fun shouldEmmitPassordInvalidState_whenPasswordNotContainsCapitalLetterNumberAndEspecialCharacters() {
        val expectedState = LoginState(
            isLoading = false,
            isLoadError = false,
            errorMessage = "",
            isUserEmpty = false,
            isPasswordEmpty = false,
            isPasswordInvalid = true,
            isUserInvalid = false
        )

        var actualState = LoginState()

        loginViewModel.states.observeForever { state ->
            state.let {
                actualState = it
            }
        }

        loginViewModel.execute(LoginIntentions.Login("user@gmail.com", "r4ad"))
        assertEquals(expectedState, actualState)
    }

    @Test
    fun shouldEmmitUserInvalidState_whenUserIsNotEmailOrCPF() {
        val expectedState = LoginState(
            isLoading = false,
            isLoadError = false,
            errorMessage = "",
            isUserEmpty = false,
            isPasswordEmpty = false,
            isPasswordInvalid = false,
            isUserInvalid = true
        )

        var actualState = LoginState()

        loginViewModel.states.observeForever { state ->
            state.let {
                actualState = it
            }
        }

        loginViewModel.execute(LoginIntentions.Login("user", "T@1"))
        assertEquals(expectedState, actualState)
    }

    @Test
    fun shouldEmmitEmptyUserState_whenUserFieldIsEmpty() {
        val expectedState = LoginState(
            isLoading = false,
            isLoadError = false,
            errorMessage = "",
            isUserEmpty = true,
            isPasswordEmpty = false,
            isPasswordInvalid = false,
            isUserInvalid = false
        )

        var actualState = LoginState()

        loginViewModel.states.observeForever { state ->
            state.let {
                actualState = it
            }
        }

        loginViewModel.execute(LoginIntentions.Login("", "T@1"))
        assertEquals(expectedState, actualState)
    }

    @Test
    fun shouldEmmitEmptyPasswordState_whenPasswordFieldIsEmpty() {
        val expectedState = LoginState(
            isLoading = false,
            isLoadError = false,
            errorMessage = "",
            isUserEmpty = false,
            isPasswordEmpty = true,
            isPasswordInvalid = false,
            isUserInvalid = false
        )

        var actualState = LoginState()

        loginViewModel.states.observeForever { state ->
            state.let {
                actualState = it
            }
        }

        loginViewModel.execute(LoginIntentions.Login("user@gmail.com", ""))
        assertEquals(expectedState, actualState)
    }

    @Test
    fun shouldEmmitHasUserState_whenHasSomeUserSave() {
        val expectedState = LoginState(
            isLoading = false,
            isLoadError = false,
            errorMessage = "",
            userLogin = "user@gmail.com",
            isUserEmpty = false,
            isPasswordEmpty = false,
            isPasswordInvalid = false,
            isUserInvalid = false
        )

        var actualState = LoginState()

        loginViewModel.states.observeForever { state ->
            state.let {
                actualState = it
            }
        }

        loginViewModel.execute(LoginIntentions.Login("user@gmail.com", "T@1"))
        assertEquals(expectedState, actualState)
    }

    private fun setupLoginSuccess() {
        localTestRule.serverRule.enqueue(MockResponse().setBody(LOGIN_SUCCESS))
    }

    private fun setupLoginFailure() {
        localTestRule.serverRule.enqueue(MockResponse().setBody(LOGIN_FAILURE))
    }
}