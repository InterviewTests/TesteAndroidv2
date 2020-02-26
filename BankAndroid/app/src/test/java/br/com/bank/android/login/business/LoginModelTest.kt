package br.com.bank.android.login.business

import br.com.bank.android.CoroutineRule
import br.com.bank.android.core.retrofit.ErrorResponse
import br.com.bank.android.core.retrofit.auth.IBankAuthService
import br.com.bank.android.core.retrofit.auth.response.UserAccountResponse
import br.com.bank.android.exceptions.PasswordInvalid
import br.com.bank.android.exceptions.UserInvalid
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
class LoginModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = CoroutineRule()

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var bankAuthService: IBankAuthService

    private lateinit var loginModel: LoginModel

    private fun getValidUsers(): List<String> {
        return listOf("asdf@af.com", "asdads@oier.com", "658.097.090-10", "65809709010")
    }

    private fun getInvalidUsers(): List<String> {
        return listOf(
            "aaa",
            "vcmoraes",
            "asfasfasf",
            "asfasf@",
            "0912904",
            "3927kjdsg",
            "658.098.090-10",
            "65809809010"
        )
    }

    private fun getValidPasswords(): List<String> {
        return listOf("@vM", "aaaaR&", "@Vclaksdkl12", "32#tY")
    }

    private fun getInvalidPasswords(): List<String> {
        return listOf("@vm", "AR&", "Vclaksdkl12", "%Y")
    }

    @Before
    fun setUp() {
        loginModel = LoginModel(bankAuthService)
    }

    @Test
    fun `user is valid`() {
        var countError = 0
        val validUsers = getValidUsers()
        validUsers.forEach {
            try {
                loginModel.validateUser(it)
            } catch (error: UserInvalid) {
                countError++
            }
        }
        assert(countError == 0)
    }

    @Test
    fun `user not valid`() {
        var countError = 0
        val invalidUsers = getInvalidUsers()
        invalidUsers.forEach {
            try {
                loginModel.validateUser(it)
            } catch (error: UserInvalid) {
                countError++
            }
        }
        assert(countError == invalidUsers.size)
    }

    @Test
    fun `password is valid`() {
        var countError = 0
        val validPasswords = getValidPasswords()
        validPasswords.forEach {
            try {
                loginModel.validatePassword(it)
            } catch (error: PasswordInvalid) {
                countError++
            }
        }
        assert(countError == 0)
    }

    @Test
    fun `password not valid`() {
        var countError = 0
        val invalidsPassword = getInvalidPasswords()
        invalidsPassword.forEach {
            try {
                loginModel.validatePassword(it)
            } catch (error: PasswordInvalid) {
                countError++
            }
        }
        assert(countError == invalidsPassword.size)
    }

    @Test
    fun onLogin() = coroutineRule.runBlockingTest {
        val userAccountResponse =
            UserAccountResponse(35, "Nome", "065435", "2345", 500.0, ErrorResponse(0, ""))
        Mockito.`when`(bankAuthService.onLogin("user", "password")).thenReturn(userAccountResponse)

        val userAccount = loginModel.onLogin("user", "password")

        assert(userAccount.userId == userAccountResponse.userId)
        assert(userAccount.name == userAccountResponse.name)
        assert(userAccount.agency == userAccountResponse.agency)
        assert(userAccount.balance == userAccountResponse.balance)
    }
}