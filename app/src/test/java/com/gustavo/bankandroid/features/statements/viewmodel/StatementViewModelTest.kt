package com.gustavo.bankandroid.features.statements.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.entity.UserStatementItem
import com.gustavo.bankandroid.features.statements.usecase.StatementUseCases
import com.gustavo.bankandroid.mock.MockData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class StatementViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val fetchStatementUseCases: StatementUseCases.FetchStatementUseCase = mock()
    private val getUserInfoUseCase: StatementUseCases.GetLoggedUserInfoUseCase = mock()
    private val clearUserInfoUseCase: StatementUseCases.ClearUserInfoUseCase = mock()

    private lateinit var viewModel: StatementViewModel

    private lateinit var userInfo: UserInfo

    private lateinit var statementList: List<UserStatementItem>

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }


    @Before
    fun setUpMocks() {
        viewModel =
            StatementViewModel(fetchStatementUseCases, getUserInfoUseCase, clearUserInfoUseCase)

        userInfo = MockData.mockUserInfo()
        whenever(getUserInfoUseCase.execute()).thenReturn(Single.just(userInfo))

        statementList = listOf(
            UserStatementItem("title1", "desc1", "date", 100f),
            UserStatementItem("title2", "desc2", "date", 200f),
            UserStatementItem("title3", "desc3", "date", 3300f)
        )
        whenever(
            fetchStatementUseCases.execute(
                any()
            )
        ).thenReturn(Single.just(statementList))
    }

    @Test
    fun `if user info return statements are fetched`() {
        viewModel.fetchData()

        verify(getUserInfoUseCase).execute()
        verify(fetchStatementUseCases).execute(any())
    }

    @Test
    fun `user has the same id returned from usecase`() {
        viewModel.fetchData()

        val userInfoValue = viewModel.userInfo.value as UserInfo
        assertEquals(userInfoValue.userId, userInfo.userId)
    }

    @Test
    fun `statement list live data is fetched`() {
        viewModel.fetchData()

        val statementListValue = viewModel.statementListLiveData.value as List<UserStatementItem>
        assertEquals(statementListValue.size, statementList.size)
    }

    @Test
    fun `if loggout view gets response`(){
        viewModel.loggout()

        verify(clearUserInfoUseCase).execute()
        val loggout = viewModel.userLoggedOutLiveData.value as Boolean
        assertTrue(loggout)
    }
}