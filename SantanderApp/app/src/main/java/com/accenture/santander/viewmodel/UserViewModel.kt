package com.accenture.santander.viewmodel

import androidx.lifecycle.ViewModel

data class UserViewModel(
    val user: User = User()
) : ViewModel()