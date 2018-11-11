package br.com.santander.android.bank.login

import br.com.santander.android.bank.login.domain.interactor.LoginFailureUseCase.*
import br.com.santander.android.bank.login.domain.interactor.LoginInteractor
import br.com.santander.android.bank.login.domain.model.Account
import br.com.santander.android.bank.login.domain.model.UserAccount
import br.com.santander.android.bank.login.domain.model.UserLogin
import br.com.santander.android.bank.login.repository.LoginRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class LoginInteractorTest {

    private lateinit var loginInteractor: LoginInteractor
    private lateinit var loginRepository: LoginRepository

    @Before
    fun setup() {
        loginRepository = Mockito.mock(LoginRepository::class.java)
        loginInteractor = LoginInteractor(loginRepository)
    }

    @Test
    fun `Assert that return observable error with empty password`() {
        val login = UserLogin("a@a.com", "")
        loginInteractor.login(login)
            .test()
            .assertError(EmptyPassword)
    }

    @Test
    fun `Assert that return observable error with password missing upper`() {
        val login = UserLogin("a@a.com", "test@123")
        loginInteractor.login(login)
            .test()
            .assertError(MalformattedPassword)
    }

    @Test
    fun `Assert that return observable error with password missing digits`() {
        val login = UserLogin("a@a.com", "Test@")
        loginInteractor.login(login)
            .test()
            .assertError(MalformattedPassword)
    }

    @Test
    fun `Assert that return observable error with password missing special chars`() {
        val login = UserLogin("a@a.com", "Test123")
        loginInteractor.login(login)
            .test()
            .assertError(MalformattedPassword)
    }

    @Test
    fun `Assert that return observable error with empty user`() {
        val login = UserLogin("", "Test@1")
        loginInteractor.login(login)
            .test()
            .assertError(EmptyUser)
    }

    @Test
    fun `Assert that return observable error with invalid user type`() {
        val login = UserLogin("test", "Test@1")
        loginInteractor.login(login)
            .test()
            .assertError(InvalidUserType)
    }

    @Test
    fun `Assert that return observable user account with valid login`() {
        val login = UserLogin("a@a.com", "Test@1")
        Mockito.`when`(loginRepository.login(login)).thenReturn(mockUserAccount())
        loginInteractor.login(login)
            .test()
            .assertNoErrors()
    }

    private fun mockUserAccount(): Observable<UserAccount> {
        val account = Account(
            userId = "1",
            agency = "1234",
            balance = 0f,
            bankAccount = "bank",
            name = "User"
        )
        return Observable.just(UserAccount(account))
    }

}