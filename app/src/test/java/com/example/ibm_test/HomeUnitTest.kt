package com.example.ibm_test

import android.content.Context
import com.example.ibm_test.clean_code.home.interactor.HomeInteractorInput
import com.example.ibm_test.clean_code.home.interactor.HomeInteractorOutput
import com.example.ibm_test.clean_code.home.presenter.HomePresenterInput
import com.example.ibm_test.clean_code.home.presenter.HomePresenterOutput
import com.example.ibm_test.clean_code.home.ui.HomeActivity
import com.example.ibm_test.clean_code.home.ui.HomeActivityInput
import com.example.ibm_test.data.StatementList
import com.example.ibm_test.data.UserInfoData
import com.example.ibm_test.data.UserItemData
import com.example.ibm_test.localstorage.UserStorage
import com.example.ibm_test.service.IBMNetwork
import com.example.ibm_test.service.UserService
import com.example.ibm_test.utils.toHandlerAgency
import com.example.ibm_test.utils.toMoney
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.util.*


class HomeUnitTest {
    private var activity = mock(HomeActivityInput::class.java)
    private var service = mock(IBMNetwork::class.java)
    private var context = mock(Context::class.java)
    private var userStorage = mock(UserStorage::class.java)

    private lateinit var homeInteractorInput: HomeInteractorInput
    private lateinit var homePresenterInput: HomePresenterInput
    private lateinit var userService: UserService
    private lateinit var homeActivity: HomeActivity

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        userService = UserService(service)
        homePresenterInput = HomePresenterOutput(context)
        homePresenterInput.attachHomeInput(activity)
        homeInteractorInput = HomeInteractorOutput(homePresenterInput, userService, userStorage)
    }

    @Test
    fun test_setupDataToView() {
        val userInfoData = getUserInfoData()

        Mockito.`when`(service.getUserItemInfo(userInfoData.userId.toString())).thenReturn(getOnSuccess())

        homeInteractorInput.startApp(userInfoData)

        val userName = getUserInfoData().name
        val numberAccount = "${getUserInfoData().bankAccount} / ${getUserInfoData().agency.toHandlerAgency()}"
        val balanceAccount = getUserInfoData().balance.toMoney()

        verify(activity).setUserInfo(userName = userName, numberAccount = numberAccount, balanceAccount = balanceAccount)
    }

    @Test
    fun test_loadingItemsSuccess() {
        val userInfoData = getUserInfoData()

        Mockito.`when`(service.getUserItemInfo(userInfoData.userId.toString())).thenReturn(getOnSuccess())

        homeInteractorInput.startApp(userInfoData)

        verify(activity).setUserList(getUserItems())
    }

    @Test
    fun test_loadingItemsError() {
        val userInfoData = getUserInfoData()
        val messageError = "Error 500"

        Mockito.`when`(service.getUserItemInfo(userInfoData.userId.toString())).thenReturn(getOnError(messageError))

        homeInteractorInput.startApp(userInfoData)

        verify(activity).setError(messageError)
    }

    @Test
    fun test_loadingItemsEmpty() {
        val userInfoData = getUserInfoData()
        val message = "Lista está vazia"

        Mockito.`when`(service.getUserItemInfo(userInfoData.userId.toString())).thenReturn(getOnSuccessEmpty())

        homeInteractorInput.startApp(userInfoData)

        Mockito.`when`(context.getString(R.string.empty_list)).thenReturn(message)

        verify(activity).setError(message)
    }

    @Test
    fun test_logout() {
        homeInteractorInput.onLogout()
        verify(userStorage).clearData()
        verify(activity).startActivityLogin()

    }

    private fun getOnSuccess(): Single<StatementList> = Single.create {
        it.onSuccess(getStatementList())
    }

    private fun getOnError(message: String): Single<StatementList> = Single.create {
        it.onError(Throwable(message))
    }

    private fun getOnSuccessEmpty() : Single<StatementList> = Single.create {
        it.onSuccess(StatementList(items = emptyList()))
    }

    private fun getStatementList() = StatementList(items = getUserItems())

    private fun getUserItems(): List<UserItemData> = listOf(
        UserItemData("Pagamento", "Conta de luz", Date("2020/08/15"), -50.0),
        UserItemData("TED", "Rafael",  Date("2020/09/15"), 1000.5)
    )

    private fun getUserInfoData() = UserInfoData(
        userId = 1,
        name = "Jose da Silva Teste",
        balance = 3.3445,
        bankAccount = "2050",
        agency = "012314564"
    )

}