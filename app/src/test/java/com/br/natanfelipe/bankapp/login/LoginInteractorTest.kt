package com.br.natanfelipe.bankapp.login

import com.br.natanfelipe.bankapp.RxImmediateSchedulerRule
import com.br.natanfelipe.bankapp.api.RestApi
import com.br.natanfelipe.bankapp.interactor.LoginInteractor
import com.br.natanfelipe.bankapp.interfaces.home.LoginPresenterInput
import com.br.natanfelipe.bankapp.presenter.LoginPresenter
import com.br.natanfelipe.bankapp.view.login.LoginRequest
import com.br.natanfelipe.bankapp.view.login.LoginResponse
import org.junit.*

class LoginInteractorTest {

    lateinit var loginPresenterInputSpy : LoginActivityOutputSpy
    lateinit var loginRequest: LoginRequest
    lateinit var loginResponse: LoginResponse
    lateinit var api: RestApi
    lateinit var loginPresenter: LoginPresenter
    lateinit var loginInteractor : LoginInteractor

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Before
    fun setUp() {
        loginRequest = LoginRequest()
        loginResponse = LoginResponse()
        api = RestApi()
        loginRequest.api = api
        loginPresenter = LoginPresenter()
    }

    @After
    fun tearDown() {
    }



    @Test
    fun fetchLoginMetaData_with_vaildInput() {
        loginInteractor = LoginInteractor()
        api.doLogin("Test@test.com","T@1")
        loginInteractor.output = LoginActivityOutputSpy()
        loginPresenterInputSpy = LoginActivityOutputSpy()

        loginInteractor.fetchLoginMetaData(loginRequest, "teste", "Test@1")
        loginPresenterInputSpy.presentLoginMetaData(loginResponse)
        Assert.assertEquals("Jose da Silva Teste", loginResponse.userAccount.name)
    }

    inner class LoginActivityOutputSpy : LoginPresenterInput {
        override fun presentLoginMetaData(response: LoginResponse) {
            loginResponse = response
        }
    }




}

