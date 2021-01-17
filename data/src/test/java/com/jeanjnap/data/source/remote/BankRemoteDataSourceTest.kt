package com.jeanjnap.data.source.remote

import com.jeanjnap.data.mapper.StatementSummaryResponseToStatementListMapper
import com.jeanjnap.data.mapper.UserDataResponseToUserAccountMapper
import com.jeanjnap.data.model.response.StatementSummaryResponse
import com.jeanjnap.data.model.response.UserDataResponse
import com.jeanjnap.data.source.remote.service.BankService
import com.jeanjnap.domain.entity.ErrorResponse
import com.jeanjnap.domain.entity.Statement
import com.jeanjnap.domain.entity.SuccessResponse
import com.jeanjnap.domain.entity.UserAccount
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.ArgumentMatchers.anyString
import retrofit2.Response
import java.io.IOException

class BankRemoteDataSourceTest {

    private lateinit var dataSource: BankRemoteDataSource

    @MockK
    private lateinit var bankService: BankService

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        dataSource = BankRemoteDataSourceImpl(
            bankService = bankService,
            userDataResponseToUserAccountMapper = UserDataResponseToUserAccountMapper(),
            statementListMapper = StatementSummaryResponseToStatementListMapper()
        )
    }

    @Test
    fun login_withSuccessResponse_shouldReturnSuccessResponseOfUserAccount() {
        coEvery {
            bankService.loginAsync(any(), any())
        } returns CompletableDeferred(mockSuccessLoginResponse())

        runBlocking {
            assertTrue(dataSource.login(anyString(), anyString()) is SuccessResponse<UserAccount>)
        }
    }

    @Test
    fun login_withErrorResponse_shouldReturnErrorResponseOfUserAccount() {
        coEvery {
            bankService.loginAsync(any(), any())
        } returns CompletableDeferred(mockErrorLoginResponse())

        runBlocking {
            assertTrue(dataSource.login(anyString(), anyString()) is ErrorResponse<UserAccount>)
        }
    }

    @Test
    fun login_withIOException_shouldReturnErrorResponseOfUserAccount() {
        coEvery { bankService.loginAsync(any(), any()) } throws IOException()

        runBlocking {
            assertTrue(dataSource.login(anyString(), anyString()) is ErrorResponse<UserAccount>)
        }
    }

    @Test
    fun getStatements_withSuccessResponse_shouldReturnsSuccessResponseOfStatementList() {
        coEvery {
            bankService.getStatementsAsync(any())
        } returns CompletableDeferred(mockSuccessStatementsResponse())

        runBlocking {
            assertTrue(dataSource.getStatements(anyLong()) is SuccessResponse<List<Statement>>)
        }
    }

    @Test
    fun getStatements_withErrorResponse_shouldReturnErrorResponseOfStatementList() {
        coEvery {
            bankService.getStatementsAsync(any())
        } returns CompletableDeferred(mockErrorStatementsResponse())

        runBlocking {
            assertTrue(dataSource.getStatements(anyLong()) is ErrorResponse<List<Statement>>)
        }
    }

    @Test
    fun getStatements_withIOException_shouldReturnErrorResponseOfStatementList() {
        coEvery { bankService.getStatementsAsync(any()) } throws IOException()

        runBlocking {
            assertTrue(dataSource.getStatements(anyLong()) is ErrorResponse<List<Statement>>)
        }
    }

    private fun mockSuccessLoginResponse(): Response<UserDataResponse> {
        return Response.success(mockk(relaxed = true))
    }

    private fun mockErrorLoginResponse(): Response<UserDataResponse> {
        return Response.error(
            mockk<ResponseBody>(),
            mockk(relaxed = true)
        )
    }

    private fun mockSuccessStatementsResponse(): Response<StatementSummaryResponse> {
        return Response.success(mockk(relaxed = true))
    }

    private fun mockErrorStatementsResponse(): Response<StatementSummaryResponse> {
        return Response.error(
            mockk<ResponseBody>(),
            mockk(relaxed = true)
        )
    }
}