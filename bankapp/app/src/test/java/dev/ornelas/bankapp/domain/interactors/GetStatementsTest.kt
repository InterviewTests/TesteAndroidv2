package dev.ornelas.bankapp.domain.interactors

import dev.ornelas.banckapp.domain.interactors.GetStatements
import dev.ornelas.banckapp.domain.model.User
import dev.ornelas.banckapp.domain.model.datatype.Result
import dev.ornelas.banckapp.domain.repository.StatementRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

class GetStatementsTest {

    @ExperimentalCoroutinesApi
    @Test
    fun verifyUseCaseCallRepository() = runBlockingTest {
        //Giver
        val statementRepository = mockk<StatementRepository>()
        val getStatements = GetStatements(statementRepository)
        val idUser = 1

        coEvery { statementRepository.GetStatements(idUser) } returns Result.success(emptyList())

        //When
        getStatements(idUser)

        //Then
        coVerify {
            statementRepository.GetStatements(idUser)
        }
    }


    @ExperimentalCoroutinesApi
    @Test
    fun verifyResultWhenRepoMockReturnSuccessState() = runBlockingTest {

        //Giver
        val statementRepository = mockk<StatementRepository>()
        val getStatements = GetStatements(statementRepository)
        val idUser = 1

        coEvery { statementRepository.GetStatements(idUser) } returns Result.success(emptyList())

        //When
        val expected = Result.success<List<User>>(emptyList())
        val result  = getStatements(idUser)

        //Then
        coVerify {
            statementRepository.GetStatements(idUser)
        }

        Assert.assertEquals(expected,result)

    }
}