package br.com.flaviokreis.santanderv2.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.flaviokreis.santanderv2.data.repositories.LoginRepository
import br.com.flaviokreis.santanderv2.data.response.LoginResponse
import br.com.flaviokreis.santanderv2.utils.Validators
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {

    private val userFieldError = MutableLiveData<String>()
    private val userPasswordError = MutableLiveData<String>()
    private val onLoginChanged = MediatorLiveData<LoginResponse>()
    private val loginInProgress = MutableLiveData<Boolean>()

    private var username = ""
    private var password = ""

    val onUserFieldError: LiveData<String>
        get() = userFieldError

    val onPasswordFieldError: LiveData<String>
        get() = userPasswordError

    val onLoginResponse: LiveData<LoginResponse>
        get() = onLoginChanged

    val onLoginInProgress: LiveData<Boolean>
        get() = loginInProgress

    fun onUsernameChange(username: CharSequence) {
        this.username = username.toString()
        clearErrorFields()
    }

    fun onPasswordChange(password: CharSequence) {
        this.password = password.toString()
        clearErrorFields()
    }

    private fun clearErrorFields() {
        userFieldError.value = ""
        userPasswordError.value = ""
    }

    fun login() {
        if (!isValid()) return

        loginInProgress.value = true
        onLoginChanged.addSource(repository.login(username, password)) {
            onLoginChanged.value = it
        }
    }

    private fun isValid(): Boolean {
        var result = true
        if (!Validators.isUsernameValid(username)) {
            userFieldError.value = "Usuário inválido"
            result = false
        }

        if(!Validators.isPasswordValid(password)) {
            userPasswordError.value = "Password inválido"
            result = false
        }

        return result
    }
}
