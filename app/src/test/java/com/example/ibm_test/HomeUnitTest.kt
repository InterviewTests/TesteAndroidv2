package com.example.ibm_test

import android.content.Context
import com.example.ibm_test.clean_code.home.interactor.HomeInteractorInput
import com.example.ibm_test.clean_code.home.interactor.HomeInteractorOutput
import com.example.ibm_test.clean_code.home.presenter.HomePresenterInput
import com.example.ibm_test.clean_code.home.presenter.HomePresenterOutput
import com.example.ibm_test.clean_code.home.ui.HomeActivityInput
import com.example.ibm_test.data.*
import com.example.ibm_test.localstorage.UserStorage
import com.example.ibm_test.localstorage.UserStorageImp
import com.example.ibm_test.service.IBMNetwork
import com.example.ibm_test.service.UserService
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
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

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        userService = UserService(service)
        homePresenterInput = HomePresenterOutput(context)
        homePresenterInput.attachHomeInput(activity)
        homeInteractorInput = HomeInteractorOutput(homePresenterInput, userService, userStorage)
    }

    @Test
    fun test_setupUserDataToView() {
    }

    @Test
    fun test_loadingItemsSuccess() {
    }

    @Test
    fun test_loadingItemsError() {

    }

    @Test
    fun test_loadingItemsEmpty() {

    }

    @Test
    fun test_logout() {

    }

    private fun getOnSuccess(): Single<StatementList> = Single.create {
        it.onSuccess(getStatementList())
    }

    private fun getOnError(message: String): Single<UserAccount> = Single.create {
        it.onError(Throwable(message))
    }

    private fun getOnSuccessEmpty() : Single<StatementList> = Single.create {
        it.onSuccess(StatementList(items = emptyList()))
    }

    private fun getStatementList() = StatementList(items = getUserItems())

    private fun getUserItems(): List<UserItemData> = listOf(
        UserItemData("Pagament", "Conta de luz", Date("2018-08-15"), -50.0),
        UserItemData("TED", "Rafael", Date("2017-08-15"), 1000.5)
    )

    private fun getUserInfoData() = UserInfoData(
        userId = 1,
        name = "Jose da Silva Teste",
        balance = 3.3445,
        bankAccount = "2050",
        agency = "012314564"
    )

}