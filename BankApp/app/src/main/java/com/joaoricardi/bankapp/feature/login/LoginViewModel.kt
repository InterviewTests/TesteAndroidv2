package com.joaoricardi.bankapp.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joaoricardi.bankapp.models.login.UserAccont
import com.joaoricardi.bankapp.service.repositoy.login.LoginRepository
import com.joaoricardi.bankapp.service.repositoy.login.LoginRepositoryContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel : ViewModel(){
    private val loginRepository: LoginRepositoryContract = LoginRepository()

    private val _userAccont = MutableLiveData<UserAccont>()
    val userAccont: LiveData<UserAccont>
        get() = _userAccont

    private val _state = MutableLiveData<ScreenState>()
    val state: LiveData<ScreenState>
        get() = _state

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    fun login(email: String, password: String){
        _state.postValue(ScreenState.Loading)
        coroutineScope.launch {
            val defState = loginRepository.login(email,password)
            try{
                val responseDef = defState.await()
                _userAccont.postValue(responseDef.userAccount)

            }catch (e: Exception){
                _state.postValue(ScreenState.Error)
            }
        }
    }


    sealed class ScreenState {
        object Loading: ScreenState()
        object Initial: ScreenState()
        object Error: ScreenState()
    }
}