package br.com.bank.android.login.presentation.model

import br.com.bank.android.CoroutineRule
import br.com.bank.android.exceptions.PasswordInvalid
import br.com.bank.android.exceptions.UserInvalid
import br.com.bank.android.login.business.LoginBusiness
import br.com.bank.android.login.handler.LoginHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LoginViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = CoroutineRule()

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var loginBusiness: LoginBusiness

    @Mock
    lateinit var loginHandler: LoginHandler

    private lateinit var model: LoginViewModel
    private val USER_TEST = "user"
    private val PASSWORD_TEST = "password"

    @Before
    fun beforeTest() {
        model = LoginViewModel(loginBusiness, loginHandler)
        model.user.set(USER_TEST)
        model.password.set(PASSWORD_TEST)
    }

    @Test
    fun getUser() {
        assert(model.user.get().equals(USER_TEST))
    }

    @Test
    fun getPassword() {
        assert(model.password.get().equals(PASSWORD_TEST))
    }

    @Test
    fun `Success Login`() = coroutineRule.runBlockingTest {
        model.onLogin()

        verify(loginBusiness, times(1)).validateUser(USER_TEST)
        verify(loginBusiness, times(1)).validatePassword(PASSWORD_TEST)
        verify(loginHandler, times(1)).setLoading(true)
        verify(loginHandler, times(1)).setLoading(false)
    }

    @Test
    fun `Invalid user Login`() = coroutineRule.runBlockingTest {
        val userInvalid = UserInvalid()

        `when`(loginBusiness.validateUser(USER_TEST)).thenThrow(userInvalid)

        model.onLogin()

        verify(loginBusiness, times(1)).validateUser(USER_TEST)
        verify(loginBusiness, times(0)).validatePassword(PASSWORD_TEST)
        verify(loginHandler, times(1)).setLoading(false)
        verify(loginHandler, times(1)).onError(userInvalid)
    }

    @Test
    fun `Invalid password Login`() = coroutineRule.runBlockingTest {
        val passwordInvalid = PasswordInvalid()

        `when`(loginBusiness.validatePassword(PASSWORD_TEST)).thenThrow(passwordInvalid)

        model.onLogin()

        verify(loginBusiness, times(1)).validateUser(USER_TEST)
        verify(loginBusiness, times(1)).validatePassword(PASSWORD_TEST)
        verify(loginHandler, times(1)).setLoading(false)
        verify(loginHandler, times(1)).onError(passwordInvalid)
    }
}