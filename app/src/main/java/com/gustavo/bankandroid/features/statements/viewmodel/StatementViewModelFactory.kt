package com.gustavo.bankandroid.features.statements.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gustavo.bankandroid.features.statements.injection.StatementInjection
import com.gustavo.bankandroid.features.statements.injection.StatementModule

@Suppress("UNCHECKED_CAST")
class StatementViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StatementViewModel(
            module.fetchStatementUseCase,
            module.getLoggedUserInfoUseCase,
            module.clearUserInfoUseCase
        ) as T
    }

    private val module: StatementModule
        get() = (context as StatementInjection).statementModule

}