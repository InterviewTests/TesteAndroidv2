package com.accenture.santander.viewmodel

import androidx.lifecycle.ViewModel

data class AccountViewModel(
    val account: Account = Account()
) : ViewModel()