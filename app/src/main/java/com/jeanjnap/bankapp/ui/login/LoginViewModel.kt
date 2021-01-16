package com.jeanjnap.bankapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jeanjnap.bankapp.ui.base.BaseViewModel
import com.jeanjnap.infrastructure.network.Network
import kotlinx.coroutines.delay

class LoginViewModel(
    network: Network
): BaseViewModel(network) {

    val passwordError: LiveData<Boolean> get() = _passwordError
    val usernameError: LiveData<Boolean> get() = _usernameError

    private val _passwordError = MutableLiveData<Boolean>()
    private val _usernameError = MutableLiveData<Boolean>()

    private val form = LoginForm()

    fun onUserNameChanged(text: CharSequence) {
        form.user = text.toString()
        _usernameError.value = false
    }

    fun onPasswordChanged(text: CharSequence) {
        form.pass = text.toString()
        _passwordError.value = false
    }

    fun onLoginClick() {
        _usernameError.value = !form.isValidUsername()
        _passwordError.value = !form.isValidPassword()
        if (form.isFormValid()) {
            launchDataLoad {
                delay(2000)
            }
        }
    }
}