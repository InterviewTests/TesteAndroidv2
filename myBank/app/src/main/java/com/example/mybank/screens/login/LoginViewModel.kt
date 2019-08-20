package com.example.mybank.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybank.data.Repository
import com.example.mybank.data.remote.model.RecordUserResponse
import com.example.mybank.utils.Exceptions.PasswordInvalid
import com.example.mybank.utils.Exceptions.UsernameInvalid
import com.example.mybank.utils.Helper
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject
constructor(private val repository: Repository): ViewModel() {

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _loggedIn = MutableLiveData<Boolean>()
    val loggedInd: LiveData<Boolean> = _loggedIn


    init {
        Log.i("LoginViewModel", "Iniciado View Model")
    }

    fun login (username: String, password: String) {
        if (!validateUserName(username)) return
        if (!validatePassWord(password)) return

        viewModelScope.launch {

            _loading.value = true

            try {
                val loginResponse = repository.login(username, password)
                _loggedIn.value = loginResponse?.error?.code == null
            } catch (e: Throwable) {
                _error.value = e
            }

            _loading.value = false

        }
    }

    private fun validateUserName(username: String) :Boolean {
        if (!Helper.isCPF(username) && !Helper.isEmailValid(username)) {
            viewModelScope.launch {
                _error.value = UsernameInvalid()
            }
            return false
        }
        return true
    }

    private fun validatePassWord(password: String) : Boolean {
        if (!Helper.validatePassword(password)) {
            viewModelScope.launch {
                _error.value = PasswordInvalid()
            }
            return false
        }
        return true
    }
}