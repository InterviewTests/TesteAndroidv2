package com.paulokeller.bankapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paulokeller.bankapp.data.repositories.Repository
import com.paulokeller.bankapp.data.services.Client

class LoginViewModelFactory(private val repository: Repository, private val client: Client) : ViewModelProvider.NewInstanceFactory()  {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repository, client) as T
    }
}