package com.accenture.santander.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class AccountViewModel(
    val account: Account = Account(),
    val accountLiveData: MutableLiveData<Account> = MutableLiveData(account)
    ) : ViewModel()