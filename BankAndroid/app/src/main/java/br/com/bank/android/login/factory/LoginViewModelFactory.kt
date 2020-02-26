package br.com.bank.android.login.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.bank.android.login.data.LoginData
import br.com.bank.android.login.handler.LoginHandler
import br.com.bank.android.login.presentation.model.LoginViewModel

class LoginViewModelFactory(
    private val loginData: LoginData,
    private val loginHandler: LoginHandler
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(loginData, loginHandler) as T
    }
}