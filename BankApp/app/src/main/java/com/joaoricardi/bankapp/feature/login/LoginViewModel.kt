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


    sealed class ScreenState {
        object Loading: ScreenState()
        object Initial: ScreenState()
        object Error: ScreenState()
    }
}