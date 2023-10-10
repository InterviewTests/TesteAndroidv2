package com.jisellemartins.bank.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jisellemartins.bank.extensions.toLiveData
import com.jisellemartins.bank.model.Login
import com.jisellemartins.bank.model.User
import com.jisellemartins.bank.network.Output
import com.jisellemartins.bank.repositories.BankRepository
import com.jisellemartins.bank.utils.CpfUtil
import com.jisellemartins.bank.utils.EmailUtil
import com.jisellemartins.bank.utils.EmailUtil.Companion.isValidEmail
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: BankRepository) : ViewModel() {
    private val _loginLiveData = MutableLiveData<Output<User?>>()
    val loginLiveData = _loginLiveData.toLiveData()


    fun login(login: Login) {
        viewModelScope.launch {
            repository.postLogin(login).collect {
                _loginLiveData.value = it
            }
        }
    }

    fun checkFields(login: Login) {
        if (login.user.isBlank() || login.password.isBlank()) {
            _loginLiveData.value = Output.Error(User("0", "", "Os campos não podem ser vazios"))
        } else {
            checkUser(login)
        }
    }

    fun checkUser(login: Login) {
        if (login.user.isValidEmail() || CpfUtil.myValidateCPF(login.user)) {
            checkPassword(login)
        } else _loginLiveData.value = Output.Error(User("0", "", "Email ou CPF inválido"))
    }

    fun checkPassword(login: Login) {
        if (login.password.contains("[A-Z]".toRegex())) {
            if (login.password.contains("[0-9]".toRegex())) {
                if (login.password.contains("[!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())) {
                    login(login)
                } else _loginLiveData.value = Output.Error(
                    User(
                        "0",
                        "",
                        "A senha precisa ter ao menos um caractere especial"
                    )
                )
            } else _loginLiveData.value =
                Output.Error(User("0", "", "A senha precisa ter ao menos um número"))

        } else _loginLiveData.value =
            Output.Error(User("0", "", "A senha precisa ter ao menos uma letra maiúscula"))

    }
}


