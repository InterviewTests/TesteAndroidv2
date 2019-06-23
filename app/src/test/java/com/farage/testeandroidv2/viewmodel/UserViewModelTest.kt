package com.farage.testeandroidv2.viewmodel

import android.content.Intent
import com.farage.testeandroidv2.domain.model.UserAccount
import com.farage.testeandroidv2.domain.usecase.UserLoginUseCase
import com.farage.testeandroidv2.router.LoginRouter
import com.farage.testeandroidv2.ui.LoginActivity
import com.farage.testeandroidv2.util.ResultState
import com.farage.testeandroidv2.util.ResultType
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class UserViewModelTest {

    private val mockLoginUseCase: UserLoginUseCase = mock()
    private val mockLoginRouter: LoginRouter = mock()
    private val mockLoginActivity: LoginActivity = mock()
    private val viewModel = UserViewModel(mockLoginUseCase, mockLoginRouter)

    @Test
    fun handleUserLogin_shouldReturnResult() = runBlocking {
        whenever(mockLoginUseCase.executeLogin("", ""))
            .thenReturn(
                ResultState.success(UserAccount("", "", "", "", ""))
            )

        viewModel.getScreenStateLiveData.observeForever {}
        viewModel.callHandleUserLogin("", "")
        assertEquals(viewModel.getScreenStateLiveData.value!!.resultType, ResultType.SUCCESS)
    }

    @Test
    fun callLoginRouter_ShouldReturnIntent(){
        whenever(mockLoginRouter.moveUserToHome(mockLoginActivity, null)).thenReturn(Intent())
        viewModel.getRouterScreen.observeForever {  }
        viewModel.callLoginRouter(mockLoginActivity, null)
        assertNotNull(viewModel.getRouterScreen)
    }


}