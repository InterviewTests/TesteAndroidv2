package com.ygorcesar.testeandroidv2.login

import com.ygorcesar.testeandroidv2.BaseTests
import com.ygorcesar.testeandroidv2.base.extensions.empty
import com.ygorcesar.testeandroidv2.login.data.LoginRepository
import com.ygorcesar.testeandroidv2.login.domain.LoginBusiness
import com.ygorcesar.testeandroidv2.login.domain.LoginInteractor
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as whenever

class LoginValidationInteractorTests : BaseTests() {

    @Mock
    lateinit var repository: LoginRepository

    private lateinit var interactor: LoginInteractor

    @Before
    fun setUp() {
        interactor = LoginInteractor(repository)
    }

    @Test
    fun `Should authenticate with email and password success`() {
        whenever(repository.login(emailValid, passwordValid))
            .thenReturn(Single.just(USER))

        val testObserver = interactor.login(emailValid, passwordValid).test()

        testObserver.awaitTerminalEvent()

        testObserver.assertComplete()
        testObserver.assertNoErrors()

        verify(repository).login(emailValid, passwordValid)
    }

    @Test
    fun `Should authenticate with cpf and password success`() {
        whenever(repository.login(cpfValid, passwordValid))
            .thenReturn(Single.just(USER))

        val testObserver = interactor.login(cpfValid, passwordValid).test()

        testObserver.awaitTerminalEvent()

        testObserver.assertComplete()
        testObserver.assertNoErrors()

        verify(repository).login(cpfValid, passwordValid)
    }

    @Test
    fun `Should throw user is empty`() {
        val testObserver = interactor.login(String.empty(), passwordValid).test()

        testObserver.awaitTerminalEvent()

        testObserver.assertNotComplete()
        testObserver.assertError(LoginBusiness.UserInvalid)
    }

    @Test
    fun `Should throw user email invalid error`() {
        val testObserver = interactor.login(emailInvalid, passwordValid).test()

        testObserver.awaitTerminalEvent()

        testObserver.assertNotComplete()
        testObserver.assertError(LoginBusiness.UserInvalid)
    }

    @Test
    fun `Should throw user cpf invalid error`() {
        val testObserver = interactor.login(cpfInvalid, passwordValid).test()

        testObserver.awaitTerminalEvent()

        testObserver.assertNotComplete()
        testObserver.assertError(LoginBusiness.UserInvalid)
    }

    @Test
    fun `Should throw password is empty`() {
        val testObserver = interactor.login(emailValid, String.empty()).test()

        testObserver.awaitTerminalEvent()

        testObserver.assertNotComplete()
        testObserver.assertError(LoginBusiness.PasswordInvalid)
    }

    @Test
    fun `Should throw password need special character`() {
        val testObserver = interactor.login(emailValid, passwordInvalidSpecialCharacter).test()

        testObserver.awaitTerminalEvent()

        testObserver.assertNotComplete()
        testObserver.assertError(LoginBusiness.PasswordNeedSpecialCharacter)
    }

    @Test
    fun `Should throw password need capitalized character`() {
        val testObserver = interactor.login(emailValid, passwordInvalidCapitalizedCharacter).test()

        testObserver.awaitTerminalEvent()

        testObserver.assertNotComplete()
        testObserver.assertError(LoginBusiness.PasswordNeedCapitalizedLetter)
    }

}