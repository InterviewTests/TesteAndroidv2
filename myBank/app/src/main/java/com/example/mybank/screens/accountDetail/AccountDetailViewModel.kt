package com.example.mybank.screens.accountDetail

import android.util.Log
import androidx.lifecycle.*
import com.example.mybank.data.Repository
import com.example.mybank.data.local.entity.User
import com.example.mybank.data.remote.model.RecordTransactionResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountDetailViewModel @Inject
constructor(private val repository: Repository) :ViewModel() {

    private var _user = repository.getUser()
    val user: LiveData<User> = _user

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _transactions = MutableLiveData<RecordTransactionResponse>()
    val transactions: LiveData<RecordTransactionResponse> = _transactions

    fun getUserTransactions(userId: Int?) {
        viewModelScope.launch {

            _loading.value = true

            try {
                val transactionsResponse = repository.getUserTRansactions(userId!!)
                _transactions.value = transactionsResponse
            } catch(e: Throwable) {
                _error.value = e
            }

            _loading.value = false

        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                repository.deleteUser()
                _user = repository.getUser()
            } catch (e: Throwable) {
                throw e
            }
        }
    }
}