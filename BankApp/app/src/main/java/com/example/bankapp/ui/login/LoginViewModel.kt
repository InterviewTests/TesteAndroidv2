package com.example.bankapp.ui.login

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.domain.executores.RealizarLoginExecutor

class LoginViewModel(private val loginExecutor: RealizarLoginExecutor, val app: Application) :
    ViewModel() {
}