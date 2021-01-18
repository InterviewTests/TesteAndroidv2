package com.jeanjnap.bankapp.ui.login

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.jeanjnap.bankapp.ui.base.BaseViewModel
import com.jeanjnap.domain.entity.Response
import com.jeanjnap.domain.entity.SuccessResponse
import com.jeanjnap.domain.entity.UserAccount
import com.jeanjnap.domain.usecase.BankUseCase
import com.jeanjnap.infrastructure.network.Network

class LoginViewModel(
    network: Network,
    private val bankUseCase: BankUseCase
): BaseViewModel(network) {

    val username: LiveData<String> get() = _username
    val passwordError: LiveData<Boolean> get() = _passwordError
    val usernameError: LiveData<Boolean> get() = _usernameError
    val loginSuccess: LiveData<UserAccount> get() = _loginSuccess

    private val _username = MutableLiveData<String>()
    private val _passwordError = MutableLiveData<Boolean>()
    private val _usernameError = MutableLiveData<Boolean>()
    private val _loginSuccess = MutableLiveData<UserAccount>()

    private val form = LoginForm()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        bankUseCase.getUser()?.let {
            form.user = it
            _username.value = it
        }
    }

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
                bankUseCase.login(form.user, form.pass).handleLoginResult()
            }
        }
    }

    private fun Response<UserAccount>.handleLoginResult() {
        if (this is SuccessResponse) {
            _loginSuccess.value = body
        } else {
            displayError(resourcesString.genericError)
        }
    }
}
