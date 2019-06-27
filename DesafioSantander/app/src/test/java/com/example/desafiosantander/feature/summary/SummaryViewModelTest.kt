package com.example.desafiosantander.feature.summary

import com.example.desafiosantander.rule.BaseViewModelTest
import com.example.desafiosantander.data.model.basic.Statement
import com.example.desafiosantander.data.model.basic.UserAccount
import com.example.desafiosantander.data.model.basic.ViewModelState
import com.example.desafiosantander.data.model.response.Error
import com.example.desafiosantander.data.model.response.LoginResponse
import com.example.desafiosantander.data.model.response.StatementResponse
import com.example.desafiosantander.data.repository.hawk.HawkContract
import com.example.desafiosantander.mock.LoginMock
import com.example.desafiosantander.mock.SummaryMock
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given

class SummaryViewModelTest : BaseViewModelTest() {

    private val summaryViewModel: SummaryViewModel by inject()

    @Before
    fun setupContract() {
        declareMock<HawkContract> {
            given(this.getObject("USER")).willReturn(getUserAccountSuccess())
            given(this.contains("USER")).willReturn(true)
            given(this.delete()).willReturn(true)
        }
    }

    @Test
    fun shouldReturnStatementListSuccess() {
        mockResponse(SummaryMock.STATEMENT_LIST_SUCCESS)
        val expected =
            ViewModelState(status = ViewModelState.Status.SUCCESS, model = getStatementListSuccess(), errors = null)
        var actual = ViewModelState<List<Statement>>(status = ViewModelState.Status.LOADING)

        summaryViewModel.getStatementLiveData().observeForever {
            actual = it
        }

        summaryViewModel.statementList(1)
        assertEquals(expected, actual)
    }

    @Test
    fun shouldReturnStatementListError() {
        mockResponse(SummaryMock.STATEMENT_LIST_ERROR)
        val expected =
            ViewModelState(status = ViewModelState.Status.ERROR, model = null, errors = getStatementListError())
        var actual = ViewModelState<List<Statement>>(status = ViewModelState.Status.LOADING)

        summaryViewModel.getStatementLiveData().observeForever {
            actual = it
        }

        summaryViewModel.statementList(1)
        assertEquals(expected, actual)
    }

    @Test
    fun shouldReturnUserSavedSuccess() {
        mockResponse(SummaryMock.STATEMENT_LIST_SUCCESS)

        val expected = getUserAccountSuccess()
        var actual: UserAccount? = null

        summaryViewModel.getUserLiveData().observeForever {
            actual = it
        }

        summaryViewModel.getUserSaved()
        assertEquals(expected, actual)
    }

    @Test
    fun shouldLogoutSuccess() {
        val expected = true
        var actual = false

        summaryViewModel.logoutLiveData().observeForever {
            actual = it
        }

        summaryViewModel.logout()
        assertEquals(expected, actual)
    }

    private fun getStatementListSuccess(): List<Statement>? {
        val collectionType = object : TypeToken<StatementResponse>() {}.type
        val response =
            GsonBuilder().create().fromJson(SummaryMock.STATEMENT_LIST_SUCCESS, collectionType) as StatementResponse
        return response.statementList
    }

    private fun getStatementListError(): Error? {
        val collectionType = object : TypeToken<StatementResponse>() {}.type
        val response =
            GsonBuilder().create().fromJson(SummaryMock.STATEMENT_LIST_ERROR, collectionType) as StatementResponse
        return response.error
    }

    private fun getUserAccountSuccess(): UserAccount? {
        val collectionType = object : TypeToken<LoginResponse>() {}.type
        val responseObject: LoginResponse =
            GsonBuilder().create().fromJson(LoginMock.LOGIN_SUCCESS, collectionType) as LoginResponse
        return responseObject.userAccount
    }

}