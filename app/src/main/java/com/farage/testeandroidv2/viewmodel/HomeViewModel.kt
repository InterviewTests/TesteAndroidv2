package com.farage.testeandroidv2.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.farage.testeandroidv2.domain.model.StatementsModel
import com.farage.testeandroidv2.domain.usecase.StatementsUseCase
import com.farage.testeandroidv2.util.ResultState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HomeViewModel(private val statementsUseCase: StatementsUseCase) : ViewModel() {

    private val screenState: MutableLiveData<ResultState<List<StatementsModel>>> = MutableLiveData()

    val getScreenState: LiveData<ResultState<List<StatementsModel>>>
        get() = screenState

    fun loadStatements(id: Int) {
        MainScope().launch {
            statementsUseCase.execute(id).let {
                screenState.postValue(it)
            }
        }
    }

}