package com.br.myapplication.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.myapplication.model.ResponseLogin
import com.br.myapplication.model.UserAccount
import com.br.myapplication.service.ApiResult
import com.br.myapplication.usecase.LoginUseCase
import com.br.myapplication.usecase.UserUseCase

class LoginViewModel(private val loginUseCase: LoginUseCase, private val userUseCase: UserUseCase) : ViewModel() {

    val liveDataResponse: MutableLiveData<ApiResult<ResponseLogin>> = MutableLiveData()

    fun doLogin(user: String, password: String)  {
        loginUseCase(LoginUseCase.Params(user, password), liveDataResponse)
    }

    fun getUser() : UserAccount? = userUseCase.getUser()

    fun saveUser(userAccount: UserAccount) = userUseCase.saveUser(userAccount)

    companion object {
        const val REGEX_PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
    }
}