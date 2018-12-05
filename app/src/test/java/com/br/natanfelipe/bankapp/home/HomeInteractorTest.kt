package com.br.natanfelipe.bankapp.login

import com.br.natanfelipe.bankapp.RxImmediateSchedulerRule
import com.br.natanfelipe.bankapp.api.RestApi
import com.br.natanfelipe.bankapp.interactor.HomeInteractor
import com.br.natanfelipe.bankapp.interfaces.home.HomePresenterInput
import com.br.natanfelipe.bankapp.presenter.HomePresenter
import com.br.natanfelipe.bankapp.view.home.HomeRequest
import com.br.natanfelipe.bankapp.view.home.HomeResponse
import org.junit.*

class HomeInteractorTest {

    lateinit var homePresenterInputSpy : HomeActivityOutputSpy
    lateinit var homeRequest: HomeRequest
    lateinit var homeResponse: HomeResponse
    lateinit var api: RestApi
    lateinit var homePresenter: HomePresenter
    lateinit var homeInteractor : HomeInteractor

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Before
    fun setUp() {
        homeRequest = HomeRequest()
        homeResponse = HomeResponse()
        api = RestApi()
        homeRequest.api = api
        homePresenter = HomePresenter()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getBillItem() {
        homeInteractor = HomeInteractor()
        api.loadBills()
        homeInteractor.output = HomeActivityOutputSpy()
        homePresenterInputSpy = HomeActivityOutputSpy()

        homeInteractor.fetchHomeMetaData(homeRequest)
        homePresenterInputSpy.presentHomeMetaData(homeResponse)
        Assert.assertEquals("Conta de luz", homeResponse.billsList.get(0).desc)
    }

    inner class HomeActivityOutputSpy : HomePresenterInput {
        override fun presentHomeMetaData(response: HomeResponse) {
            homeResponse = response
        }
    }
}

