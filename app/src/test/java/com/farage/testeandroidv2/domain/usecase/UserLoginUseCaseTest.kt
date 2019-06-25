package com.farage.testeandroidv2.domain.usecase

import com.farage.testeandroidv2.domain.UserRepository
import com.farage.testeandroidv2.domain.model.UserAccount
import com.farage.testeandroidv2.util.Constants
import com.farage.testeandroidv2.util.ResultState
import com.farage.testeandroidv2.util.ResultType
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class UserLoginUseCaseTest {

    private val mockUserRepository: UserRepository = mock()
    private val getUserLoginUseCase = UserLoginUseCase(mockUserRepository)

    @Test
    fun executeLogin_userWrongPassWrong_shouldReturnError() {
        runBlocking {
            val user = "loginUser"
            val pass = "password"
            val result = getUserLoginUseCase.executeLogin(user, pass)
            assertEquals(ResultType.ERROR, result.resultType)
            assertEquals(Constants.WRONG_EMAIL_CPF, result.message)
            assertNotNull(result)
        }
    }

    @Test
    fun executeLogin_validEmailWrongPassword_shouldReturnError() {
        runBlocking {
            val user = "email@email.com"
            val pass = "password"
            val result = getUserLoginUseCase.executeLogin(user, pass)
            assertEquals(ResultType.ERROR, result.resultType)
            assertEquals(Constants.WRONG_PASSWORD, result.message)
            assertNotNull(result)
        }
    }

    @Test
    fun executeLogin_validEmailAndPassword_shouldReturnSuccess() {
        runBlocking {
            val user = "email@email.com"
            val pass = "Pp@1"
            whenever(mockUserRepository.userLogin(user, pass)).thenReturn(
                ResultState.success(
                    UserAccount(
                        "",
                        "",
                        "",
                        "",
                        ""
                    )
                )
            )
            val result = getUserLoginUseCase.executeLogin(user, pass)
            assertEquals(ResultType.SUCCESS, result.resultType)
            assertNotNull(result)
        }
    }

    @Test
    fun executeLogin_validCpfAndPassword_shouldReturnSuccess() {
        runBlocking {
            val user = "40735450005"
            val pass = "Pp@1"
            whenever(mockUserRepository.userLogin(user, pass)).thenReturn(
                ResultState.success(
                    UserAccount(
                        "",
                        "",
                        "",
                        "",
                        ""
                    )
                )
            )
            val result = getUserLoginUseCase.executeLogin(user, pass)
            assertEquals(ResultType.SUCCESS, result.resultType)
            assertNotNull(result)
        }
    }

}