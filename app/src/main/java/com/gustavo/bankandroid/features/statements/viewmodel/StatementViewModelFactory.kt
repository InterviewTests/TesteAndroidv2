package com.gustavo.bankandroid.features.statements.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gustavo.bankandroid.features.statements.injection.StatementInjection

@Suppress("UNCHECKED_CAST")
class StatementViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StatementViewModel(
            fetchStatementUseCase,
            getLoggedUserInfoUseCase,
            clearUserInfoUseCase
        ) as T
    }

    private val fetchStatementUseCase
        get() = (context as StatementInjection).fetchStatementUseCase
    private val getLoggedUserInfoUseCase
        get() = (context as StatementInjection).getLoggedUserInfoUseCase
    private val clearUserInfoUseCase
        get() = (context as StatementInjection).clearUserInfoUseCase
}