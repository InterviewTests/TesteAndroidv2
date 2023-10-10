package com.jisellemartins.bank.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jisellemartins.bank.extensions.toLiveData
import com.jisellemartins.bank.model.DetailsUser
import com.jisellemartins.bank.network.Output
import com.jisellemartins.bank.repositories.BankRepository
import kotlinx.coroutines.launch

class StatementsViewModel(private val repository: BankRepository) : ViewModel() {
    private val _statementsLiveData = MutableLiveData<Output<DetailsUser?>>()
    val statementsLiveData = _statementsLiveData.toLiveData()

    fun getDetails(idUser: String) {
        viewModelScope.launch {
            repository.getDetailsUser(idUser).collect {
                _statementsLiveData.value = it
            }
        }
    }

}