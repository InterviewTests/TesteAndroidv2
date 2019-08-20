package com.example.mybank.screens.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mybank.data.Repository
import com.example.mybank.data.local.entity.User
import javax.inject.Inject

class SplashViewModel @Inject
constructor(private val repository: Repository) : ViewModel() {

    val user: LiveData<User>

    init {
        user = repository.getUser()
    }
}