package br.com.bank.android.login.presentation.model

import androidx.lifecycle.ViewModel
import br.com.bank.android.login.data.LoginData
import br.com.bank.android.login.handler.LoginHandler

class LoginViewModel(
    loginData: LoginData,
    loginHandler: LoginHandler
) : ViewModel() {
}