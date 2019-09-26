package com.gustavo.bankandroid.features.login.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gustavo.bankandroid.features.login.injection.LoginInjection
import com.gustavo.bankandroid.features.login.injection.LoginModule

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(
            module.authenticateUserUseCase,
            module.storeUserInfoUseCase,
            module.validateUserName,
            module.validatePassword
        ) as T
    }

    private val module: LoginModule
        get() = (context as LoginInjection).loginModule
}