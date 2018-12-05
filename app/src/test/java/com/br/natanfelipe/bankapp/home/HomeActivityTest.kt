package com.br.natanfelipe.bankapp.login

import com.br.natanfelipe.bankapp.RxImmediateSchedulerRule
import com.br.natanfelipe.bankapp.api.RestApi
import com.br.natanfelipe.bankapp.interactor.HomeInteractor
import com.br.natanfelipe.bankapp.interactor.LoginInteractor
import com.br.natanfelipe.bankapp.interfaces.home.HomeInteractorInput
import com.br.natanfelipe.bankapp.view.home.HomeActivity
import com.br.natanfelipe.bankapp.view.home.HomeRequest
import com.br.natanfelipe.bankapp.view.login.LoginActivity
import org.junit.*
import org.mockito.Mockito

class HomeActivityTest {

    lateinit var homeActivityMock: HomeActivity
    lateinit var api: RestApi
    lateinit var homeInteractor: HomeInteractor
    lateinit var homeRequest: HomeRequest

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }


    @Before
    fun setUp() {
        homeInteractor = HomeInteractor()
        homeRequest = HomeRequest()
        homeActivityMock = Mockito.mock(HomeActivity::class.java)
        api = RestApi()
    }

    @After
    fun tearDown() {
        homeRequest = HomeRequest()
    }

    @Test
    fun LoginActivity_ShoulNot_be_Null() {
        Assert.assertNotNull(homeActivityMock)
    }
}