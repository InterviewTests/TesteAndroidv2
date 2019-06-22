package com.farage.testeandroidv2.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Intent
import com.farage.testeandroidv2.domain.model.UserAccount
import com.farage.testeandroidv2.domain.usecase.UserLoginUseCase
import com.farage.testeandroidv2.router.LoginRouter
import com.farage.testeandroidv2.ui.LoginActivity
import com.farage.testeandroidv2.util.ResultState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class UserViewModel(
    private var userLoginUseCase: UserLoginUseCase,
    private var loginRouter: LoginRouter
) : ViewModel() {

    private val screenStateLiveData: MutableLiveData<ResultState<UserAccount>> = MutableLiveData()
    private val routerScreen: MutableLiveData<Intent> = MutableLiveData()

    val getScreenStateLiveData: LiveData<ResultState<UserAccount>>
        get() = screenStateLiveData

    val getRouterScreen: LiveData<Intent>
        get() = routerScreen

    fun callHandleUserLogin(user: String, password: String) {
        handleUserLogin(user, password)
    }

    private fun handleUserLogin(user: String, password: String) {
        MainScope().launch {
            userLoginUseCase.executeLogin(user, password).let {
                screenStateLiveData.postValue(it)
            }
        }
    }

    fun callLoginRouter(activity: LoginActivity, data: UserAccount?) =
        routerScreen.postValue(loginRouter.moveUserToHome(activity, data))


}