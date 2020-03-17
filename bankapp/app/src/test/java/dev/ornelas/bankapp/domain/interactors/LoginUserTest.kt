package dev.ornelas.bankapp.domain.interactors

import dev.ornelas.banckapp.domain.interactors.LoginUser
import dev.ornelas.banckapp.domain.model.User
import dev.ornelas.banckapp.domain.model.datatype.Result
import dev.ornelas.banckapp.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginUserTest {

    private val mockUserRepository = mockk<UserRepository>()
    val loginUser = LoginUser(mockUserRepository)


    @Test
    fun verifyErrorWhenRepoMockReturnkError() = runBlockingTest {
        coEvery {
            mockUserRepository.GetUser(any(), any())
        } returns Result.error(RuntimeException())

        val expectedResult = Result.error<Exception>(RuntimeException()).error
        val realResult = loginUser("teste", "teste").error

        Assert.assertEquals(expectedResult is RuntimeException, realResult is RuntimeException)

        coVerify {
            mockUserRepository.GetUser(any(), any())
        }
    }


    @Test
    fun verifyUseCaseCallRepository() {
        runBlocking {

            val result = Result.success(
                User(
                    id = 1,
                    name = "teste",
                    agency = "0000",
                    balance = "100",
                    bankAccount = "00001"
                )
            )
            //Given
            coEvery {
                mockUserRepository.GetUser(any(), any())
            } returns result

            //When
            val user = loginUser("teste", "teste")

            //Then
            Assert.assertEquals(result, user)
            coVerify { mockUserRepository.GetUser(any(), any()) }
        }
    }
}