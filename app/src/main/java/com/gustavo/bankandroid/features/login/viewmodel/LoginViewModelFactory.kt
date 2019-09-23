package com.gustavo.bankandroid.features.login.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gustavo.bankandroid.features.login.injection.LoginInjection

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(
            authenticateUserUseCase,
            storeUserInfoUseCase
        ) as T
    }

    private val authenticateUserUseCase
        get() = (context as LoginInjection).authenticateUserUseCase

    private val storeUserInfoUseCase
        get() = (context as LoginInjection).storeUserInfoUseCase

}