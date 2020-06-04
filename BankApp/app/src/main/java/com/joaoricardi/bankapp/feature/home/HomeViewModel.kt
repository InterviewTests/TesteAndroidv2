package com.joaoricardi.bankapp.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joaoricardi.bankapp.models.home.StateMent
import com.joaoricardi.bankapp.service.repositoy.home.HomeRepository
import com.joaoricardi.bankapp.service.repositoy.home.HomeRepositoryContarct
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel : ViewModel(){

    private val homeRepository: HomeRepositoryContarct = HomeRepository()

    private val _state = MutableLiveData<ScreenState>()
    val state: LiveData<ScreenState>
        get() = _state

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        getStatements()
    }

    private fun getStatements(){
        coroutineScope.launch {
            val defState = homeRepository.getStateMent()
            try{
                val responseDef = defState.await()
                println(responseDef)
                _state.postValue(ScreenState.Loaded(responseDef.statementList))

            }catch (e: Exception){
                _state.postValue(ScreenState.Error(e.message))
            }
        }
    }

    sealed class ScreenState {
        data class Loaded(val value:List<StateMent>): ScreenState()
        data class Error(val error: String?): ScreenState()
    }

}