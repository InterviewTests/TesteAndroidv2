package com.gustavo.bankandroid.statements.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gustavo.bankandroid.statements.injection.StatementInjection

@Suppress("UNCHECKED_CAST")
class StatementViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StatementViewModel(
            fetchStatementUseCase,
            getLoggedUserInfoUseCase
        ) as T
    }

    private val fetchStatementUseCase
        get() = (context as StatementInjection).fetchStatementUseCase
    private val getLoggedUserInfoUseCase
        get() = (context as StatementInjection).getLoggedUserInfoUseCase
}