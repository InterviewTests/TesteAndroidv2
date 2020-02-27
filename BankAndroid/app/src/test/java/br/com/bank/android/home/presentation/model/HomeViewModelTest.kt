package br.com.bank.android.home.presentation.model

import br.com.bank.android.CoroutineRule
import br.com.bank.android.home.business.HomeBusiness
import br.com.bank.android.home.presentation.data.Stataments
import br.com.bank.android.home.presentation.handler.HomeHandler
import br.com.bank.android.login.data.UserAccount
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HomeViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = CoroutineRule()

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var homeBusiness: HomeBusiness

    @Mock
    lateinit var homeHandler: HomeHandler

    private lateinit var model: HomeViewModel
    private val userAccount = UserAccount(50, "Jose", "12345", "5544", 500.0)

    @Before
    fun setUp() {
        model = HomeViewModel(homeBusiness, homeHandler)
        model.userAccount.set(userAccount)
    }

    @Test
    fun getUserAccount() {
        assert(userAccount == model.userAccount.get())
    }

    @Test
    fun logout() {
        model.logout()
        Mockito.verify(homeHandler, Mockito.times(1)).onDisconnected()
    }

    @Test
    fun loadStataments() = coroutineRule.runBlockingTest {
        val stataments = listOf(Stataments("title", "desc", "2018-04-05", 500.0))

        Mockito.`when`(homeBusiness.getStataments(userAccount.userId)).thenReturn(stataments)

        model.loadStataments()

        Mockito.verify(homeBusiness, Mockito.times(1)).getStataments(userAccount.userId)
        Mockito.verify(homeHandler, Mockito.times(1)).onStataments(stataments)
        Mockito.verify(homeHandler, Mockito.times(1)).setLoading(true)
        Mockito.verify(homeHandler, Mockito.times(1)).setLoading(false)
    }
}