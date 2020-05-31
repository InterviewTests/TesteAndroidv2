package com.example.bankapp.ui.statements

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.domain.executores.ListarStatementsExecutor

class MainViewModel(
    private val listarStatementsExecutor: ListarStatementsExecutor,
    val app: Application
) : ViewModel() {
}