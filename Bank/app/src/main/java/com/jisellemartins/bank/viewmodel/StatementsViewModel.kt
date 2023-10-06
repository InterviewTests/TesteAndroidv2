package com.jisellemartins.bank.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jisellemartins.bank.extensions.toLiveData
import com.jisellemartins.bank.model.DetailsUser
import com.jisellemartins.bank.model.Login
import com.jisellemartins.bank.model.User
import com.jisellemartins.bank.repositories.BankRepository
import kotlinx.coroutines.launch

class StatementsViewModel(private val repository: BankRepository) : ViewModel(){
    private val _liveData = MutableLiveData<DetailsUser>()
    val liveData = _liveData.toLiveData()

    fun getDetails(idUser: String) {
        viewModelScope.launch {
            _liveData.value = repository.getDetailsUser(idUser)

        }
    }

}