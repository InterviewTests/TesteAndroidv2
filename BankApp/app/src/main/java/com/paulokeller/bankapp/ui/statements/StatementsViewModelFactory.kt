package com.paulokeller.bankapp.ui.statements

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paulokeller.bankapp.data.services.Client

class StatementsViewModelFactory(private val client: Client) : ViewModelProvider.NewInstanceFactory()  {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StatementsViewModel(client) as T
    }
}