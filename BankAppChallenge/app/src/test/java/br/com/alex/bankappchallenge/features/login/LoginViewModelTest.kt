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
import br.com.alex.bankappchallenge.feature.login.LoginViewModel
import br.com.alex.bankappchallenge.repository.LoginRepositoryContract
import br.com.alex.bankappchallenge.util.LOGIN_SUCCESS
import br.com.alex.bankappchallenge.util.LocalTestRule
import br.com.alex.bankappchallenge.util.RxLocalRule
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
            given(this.getUserLogin()).willReturn("")
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

        loginViewModel.execute(LoginIntentions.Login("user", "T@1"))
        assertEquals(expectedNavigation, actualNavigation)
    }

    private fun setupLoginSuccess() {
        localTestRule.serverRule.enqueue(MockResponse().setBody(LOGIN_SUCCESS))
    }
}