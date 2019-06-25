package com.farage.testeandroidv2.viewmodel

import android.content.Intent
import com.farage.testeandroidv2.domain.usecase.StatementsUseCase
import com.farage.testeandroidv2.router.LoginRouter
import com.farage.testeandroidv2.ui.HomeActivity
import com.farage.testeandroidv2.util.ResultState
import com.farage.testeandroidv2.util.ResultType
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class HomeViewModelTest {
    private val statementsUseCase: StatementsUseCase = mock()
    private val loginRouter: LoginRouter = mock()
    private val activity: HomeActivity = mock()
    private val viewModel = HomeViewModel(statementsUseCase, loginRouter)

    @Test
    fun loadStatements_ShouldReturnResult() {
        runBlocking {
            whenever(statementsUseCase.execute(1)).thenReturn(ResultState.success(mutableListOf()))
            viewModel.getScreenState.observeForever {  }
            viewModel.loadStatements(1)
            val result = viewModel.getScreenState
            assertEquals(ResultType.SUCCESS, result.value?.resultType)
        }
    }

    @Test
    fun callLoginRouter_ShouldReturnIntent(){
        whenever(loginRouter.moveUserToLoginPage(activity)).thenReturn(Intent())
        viewModel.getRouterState.observeForever {  }
        viewModel.logout(activity)
        Assert.assertNotNull(viewModel.getRouterState)
    }

}