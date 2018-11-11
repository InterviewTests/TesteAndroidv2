package br.com.santander.android.bank.statement

import br.com.santander.android.bank.statement.domain.interactor.StatementInteractor
import br.com.santander.android.bank.statement.domain.model.Statements
import br.com.santander.android.bank.statement.repository.StatementRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class StatementInteractorTest {

    private lateinit var statementsInteractor: StatementInteractor
    private lateinit var statementsRepository: StatementRepository

    @Before
    fun setup() {
        statementsRepository = Mockito.mock(StatementRepository::class.java)
        statementsInteractor = StatementInteractor(statementsRepository)
    }

    @Test
    fun `Assert that with int userId getStatements return statements`() {
        val userId = 1
        Mockito.`when`(statementsRepository.getStatements(userId))
            .thenReturn(mockStatements())
        statementsInteractor.getStatements(userId)
            .test()
            .assertNoErrors()
    }

    private fun mockStatements(): Observable<Statements> {
        return Observable.just(Statements(ArrayList()))
    }

}