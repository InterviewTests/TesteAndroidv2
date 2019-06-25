package com.farage.testeandroidv2.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Intent
import com.farage.testeandroidv2.domain.model.StatementsModel
import com.farage.testeandroidv2.domain.usecase.StatementsUseCase
import com.farage.testeandroidv2.router.LoginRouter
import com.farage.testeandroidv2.ui.HomeActivity
import com.farage.testeandroidv2.util.ResultState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HomeViewModel(
    private val statementsUseCase: StatementsUseCase,
    private val loginRouter: LoginRouter) : ViewModel() {

    private val screenState: MutableLiveData<ResultState<List<StatementsModel>>> = MutableLiveData()
    private val routerState: MutableLiveData<Intent> = MutableLiveData()

    val getScreenState: LiveData<ResultState<List<StatementsModel>>>
        get() = screenState
    val getRouterState: LiveData<Intent>
        get() = routerState

    fun loadStatements(id: Int) {
        MainScope().launch {
            statementsUseCase.execute(id).let {
                screenState.postValue(it)
            }
        }
    }

    fun logout(activity: HomeActivity){
        loginRouter.moveUserToLoginPage(activity).let {
            routerState.postValue(it)
        }
    }

}