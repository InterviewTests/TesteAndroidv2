package com.example.desafiosantander.feature.login

import com.example.desafiosantander.rule.BaseViewModelTest
import com.example.desafiosantander.data.model.basic.UserAccount
import com.example.desafiosantander.data.model.basic.ViewModelState
import com.example.desafiosantander.data.model.response.Error
import com.example.desafiosantander.data.model.response.LoginResponse
import com.example.desafiosantander.data.repository.hawk.HawkContract
import com.example.desafiosantander.mock.LoginMock
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given

class LoginViewModelTest : BaseViewModelTest() {

    private val loginViewModel: LoginViewModel by inject()

    @Before
    fun setupHawk() {
        declareMock<HawkContract> {
            given(this.save("teste@email.com", "Teste@1")).willReturn(true)
            given(this.contains("USER_EMAIL")).willReturn(true)
            given(this.getObject("USER_EMAIL")).willReturn("teste@email.com")
        }
    }

    @Test
    fun shouldLoginSuccessWhenUserAndPasswordAreValid() {
        mockResponse(LoginMock.LOGIN_SUCCESS)

        val expected =
            ViewModelState(status = ViewModelState.Status.SUCCESS, model = getUserAccountSuccess(), errors = null)
        var actual = ViewModelState<UserAccount>(status = ViewModelState.Status.LOADING)

        loginViewModel.getLiveData().observeForever {
            actual = it
        }

        loginViewModel.login("teste@email.com", "Teste@1")
        assertEquals(expected, actual)
    }

    @Test
    fun shouldReturnLoginErrorWhenUserAndPasswordNoAreValid() {
        mockResponse(LoginMock.LOGIN_ERROR)
        val expected =
            ViewModelState(status = ViewModelState.Status.ERROR, model = null, errors = getUserAccountError())
        var actual = ViewModelState<UserAccount>(status = ViewModelState.Status.LOADING)

        loginViewModel.getLiveData().observeForever {
            actual = it
        }

        loginViewModel.login("teste@email.com", "1235")
        assertEquals(expected, actual)
    }

    @Test
    fun shouldReturnEmailUserSaved() {
        val expected = "teste@email.com"
        var actual = ""
        loginViewModel.getUserSavedData().observeForever {
            actual = it
        }

        loginViewModel.hasUserSaved()
        assertEquals(expected, actual)
    }

    private fun getUserAccountSuccess(): UserAccount? {
        val collectionType = object : TypeToken<LoginResponse>() {}.type
        val responseObject: LoginResponse =
            GsonBuilder().create().fromJson(LoginMock.LOGIN_SUCCESS, collectionType) as LoginResponse
        return responseObject.userAccount
    }

    private fun getUserAccountError(): Error? {
        val collectionType = object : TypeToken<LoginResponse>() {}.type
        val responseObject: LoginResponse =
            GsonBuilder().create().fromJson(LoginMock.LOGIN_ERROR, collectionType) as LoginResponse
        return responseObject.error
    }

}