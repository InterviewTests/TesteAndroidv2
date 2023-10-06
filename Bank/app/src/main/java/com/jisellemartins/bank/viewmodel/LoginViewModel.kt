package com.jisellemartins.bank.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jisellemartins.bank.extensions.toLiveData
import com.jisellemartins.bank.model.Login
import com.jisellemartins.bank.model.User
import com.jisellemartins.bank.repositories.BankRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: BankRepository) : ViewModel(){
    private val _liveData = MutableLiveData<User>()
    val liveData = _liveData.toLiveData()


    fun login(login: Login) {
        viewModelScope.launch {
            _liveData.value = repository.postLogin(login)

        }
    }
}


