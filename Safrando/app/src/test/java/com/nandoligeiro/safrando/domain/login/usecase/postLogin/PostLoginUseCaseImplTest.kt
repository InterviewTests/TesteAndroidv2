package com.nandoligeiro.safrando.domain.login.usecase.postLogin

import com.nandoligeiro.safrando.domain.common.checkCPF.CheckCPFUseCase
import com.nandoligeiro.safrando.domain.common.checkCPF.CheckCPFUseCaseImpl
import com.nandoligeiro.safrando.domain.common.checkEmail.CheckEmailUseCase
import com.nandoligeiro.safrando.domain.common.checkEmail.CheckEmailUseCaseImpl
import com.nandoligeiro.safrando.domain.common.currencyFormatter.CurrencyFormatterBrUseCaseImpl
import com.nandoligeiro.safrando.domain.common.currencyFormatter.CurrencyFormatterUseCase
import com.nandoligeiro.safrando.domain.login.model.LoginDomain
import com.nandoligeiro.safrando.domain.login.repository.LoginRepository
import com.nandoligeiro.safrando.domain.login.result.LoginResult
import com.nandoligeiro.safrando.domain.login.usecase.checkLogin.CheckLoginUseCase
import com.nandoligeiro.safrando.domain.login.usecase.checkLogin.CheckLoginUseCaseImpl
import com.nandoligeiro.safrando.domain.login.usecase.checkPassword.CheckPasswordUseCase
import com.nandoligeiro.safrando.domain.login.usecase.checkPassword.CheckPasswordUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.unmockkAll
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PostLoginUseCaseImplTest {


    companion object {
        const val user = "919.491.310-25"
        const val password = "Safra@2023"
        const val returnAmount = "R$ 1.000,00"
    }

    private lateinit var postLoginUseCase: PostLoginUseCase
    private lateinit var checkLoginUseCase: CheckLoginUseCase
    private lateinit var currencyFormatterUseCase: CurrencyFormatterUseCase
    private lateinit var checkCPFUseCase: CheckCPFUseCase
    private lateinit var checkEmailUseCase: CheckEmailUseCase
    private lateinit var checkPasswordUseCase: CheckPasswordUseCase


    @MockK
    lateinit var dispatcher: CoroutineDispatcher

    @MockK
    private lateinit var loginRepository: LoginRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        checkCPFUseCase = CheckCPFUseCaseImpl()
        checkEmailUseCase = CheckEmailUseCaseImpl()
        checkPasswordUseCase = CheckPasswordUseCaseImpl()
        currencyFormatterUseCase = spyk(CurrencyFormatterBrUseCaseImpl())
        checkLoginUseCase = spyk(
            CheckLoginUseCaseImpl(
                checkCPFUseCase, checkEmailUseCase, checkPasswordUseCase
            )
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `Post Login - SUCCESS`() = runTest {

        dispatcher = StandardTestDispatcher(testScheduler)
        postLoginUseCase = PostLoginUseCaseImpl(
            loginRepository,
            checkLoginUseCase,
            currencyFormatterUseCase,
            dispatcher
        )

        every { checkLoginUseCase(any(), any()) } returns true
        coEvery { currencyFormatterUseCase(any()) } returns returnAmount
        coEvery { loginRepository.postLogin(any(), any()) } returns getLoginDomain()

        val result = postLoginUseCase(user, password)

        coVerify { checkLoginUseCase(user, password) }
        coVerify { loginRepository.postLogin(user, password) }

        Assert.assertEquals(LoginResult.Success(resultLoginDomain()), result)
    }

    @Test
    fun `Post Login - FAIL CHECK LOGIN DATA INVALID`() = runTest {

        dispatcher = StandardTestDispatcher(testScheduler)
        postLoginUseCase = PostLoginUseCaseImpl(
            loginRepository,
            checkLoginUseCase,
            currencyFormatterUseCase,
            dispatcher
        )

        every { checkLoginUseCase(any(), any()) } returns false
        coEvery { currencyFormatterUseCase(any()) } returns returnAmount
        coEvery { loginRepository.postLogin(any(), any()) } returns getLoginDomain()

        val result = postLoginUseCase(user, password)

        coVerify(exactly = 0) { loginRepository.postLogin(any(), any()) }

        Assert.assertEquals(LoginResult.CheckFieldError, result)

    }

    @Test
    fun `Post Login - FAIL LOGIN SERVER`() = runTest {

        dispatcher = StandardTestDispatcher(testScheduler)
        postLoginUseCase = PostLoginUseCaseImpl(
            loginRepository,
            checkLoginUseCase,
            currencyFormatterUseCase,
            dispatcher
        )

        val e = Exception()
        every { checkLoginUseCase(any(), any()) } returns true
        coEvery { loginRepository.postLogin(any(), any()) } throws e

        val result = postLoginUseCase(user, password)

        Assert.assertEquals(LoginResult.Error(e), result)

    }

    private fun getLoginDomain() = LoginDomain(
        id = 1,
        name = "Jose da Silva Teste",
        agency = "2050",
        account = "01.11122-4",
        balance = "1000"
    )

    private fun resultLoginDomain() = LoginDomain(
        id = 1,
        name = "Jose da Silva Teste",
        agency = "2050",
        account = "01.11122-4",
        balance = "R$ 1.000,00"
    )
}
