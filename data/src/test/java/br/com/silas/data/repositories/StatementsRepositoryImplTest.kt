package br.com.silas.data.repositories

import br.com.silas.data.remote.api.ServiceTesteAndroidV2
import br.com.silas.data.remote.statements.StatementMapper
import br.com.silas.data.remote.statements.StatementsRepositoryImpl
import br.com.silas.data.remote.statements.StatementsResponse
import br.com.silas.domain.ErrorResponse
import br.com.silas.domain.statements.Statements
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StatementsRepositoryImplTest {
    @Mock
    lateinit var serviceTesteAndroidV2: ServiceTesteAndroidV2

    @Mock
    lateinit var statementMapper: StatementMapper

    lateinit var statementsRepositoryImpl: StatementsRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        statementsRepositoryImpl = StatementsRepositoryImpl(serviceTesteAndroidV2, statementMapper)
    }

    @Test
    @Throws(Exception::class)
    fun `Should return a list of statements from api when fetch is successful`() {
        val statementResponse = mock(StatementsResponse::class.java)

        val errorResponse = mock(ErrorResponse::class.java)
        val statements = mock(Statements::class.java)
        val list = listOf(statements)

        val pairResponse = Pair(errorResponse, list)


        `when`(serviceTesteAndroidV2.fetchStatements(anyInt())).thenReturn(
            Single.just(
                statementResponse
            )
        )
        `when`(statementMapper.mapperStatementsEntityToStatements(statementResponse)).thenReturn(
            pairResponse
        )


        val result = statementsRepositoryImpl.fetchStatements(152).test()

        result
            .assertNoErrors()
            .assertValue(pairResponse)
            .assertComplete()

        verify(serviceTesteAndroidV2).fetchStatements(152)
    }

    @Test
    @Throws(Exception::class)
    fun `Should return a list of statements from api when fetch is unsuccessful`() {
        val exception = Exception()

        `when`(serviceTesteAndroidV2.fetchStatements(anyInt())).thenReturn(
            Single.error(
                exception
            )
        )

        val result = statementsRepositoryImpl.fetchStatements(152).test()

        result
            .assertNoValues()
            .assertError(exception)
            .assertNotComplete()

        verify(serviceTesteAndroidV2).fetchStatements(152)
    }
}