package dev.ornelas.bankapp.domain.interactors


import dev.ornelas.banckapp.domain.interactors.SaveLoggedUser
import dev.ornelas.banckapp.domain.model.User
import dev.ornelas.banckapp.domain.repository.UserRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class SaveLoggedUserTest {

    private val mockUserRepository = mockk<UserRepository>()
    val saveLoggedUser = SaveLoggedUser(mockUserRepository)

    @Test
    fun verifyUseCaseCallRepository() {

        val user = User(
            id = 1,
            name = "teste",
            agency = "0000",
            balance = "100",
            bankAccount = "00001"
        )

        every {
            mockUserRepository.AddUser(user)
        } returns Unit

        saveLoggedUser(user)

        verify {
            mockUserRepository.AddUser(user)
        }

        confirmVerified(mockUserRepository)

    }
}