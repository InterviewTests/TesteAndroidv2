package dev.ornelas.bankapp.domain.interactors

import dev.ornelas.banckapp.domain.interactors.GetLoggedUser
import dev.ornelas.banckapp.domain.model.User
import dev.ornelas.banckapp.domain.model.datatype.Result
import dev.ornelas.banckapp.domain.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

class GetLoogedUserTest {

    @Test
    fun verifyUseCaseCallRepository() {
        //Given
        val mockUserRepository = mockk<UserRepository>()
        val getLoggedUser = GetLoggedUser(mockUserRepository)
        val user = User(id=1,bankAccount = "0",balance = "0",agency = "0",name = "test")

        every {
            mockUserRepository.GetSavedUser()
        } returns Result.success(user)

        //When
        val result = getLoggedUser()

        Assert.assertEquals( Result.success(user), result)

        verify {
            mockUserRepository.GetSavedUser()
        }

    }
}