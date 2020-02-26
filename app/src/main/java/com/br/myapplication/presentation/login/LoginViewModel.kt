package com.br.myapplication.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.myapplication.data.model.ResponseLogin
import com.br.myapplication.data.model.UserAccount
import com.br.myapplication.data.service.ApiResult
import com.br.myapplication.domain.usecase.LoginUseCase
import com.br.myapplication.domain.usecase.UserUseCase

class LoginViewModel(private val loginUseCase: LoginUseCase, private val userUseCase: UserUseCase) : ViewModel() {

    val liveDataResponse: MutableLiveData<ApiResult<ResponseLogin>> = MutableLiveData()

    fun doLogin(user: String, password: String)  {
        loginUseCase(LoginUseCase.Params(user, password)) {
            liveDataResponse.value = it
        }
    }

    fun getUser() : UserAccount? = userUseCase.getUser()

    fun saveUser(userAccount: UserAccount) = userUseCase.saveUser(userAccount)

    companion object {
        const val REGEX_PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
    }
}