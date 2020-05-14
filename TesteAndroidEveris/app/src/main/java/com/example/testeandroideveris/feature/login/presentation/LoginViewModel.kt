package com.example.testeandroideveris.feature.login.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.testeandroideveris.data.Resource
import com.example.testeandroideveris.feature.login.data.LoginDataState
import com.example.testeandroideveris.feature.login.data.LoginRequestData
import com.example.testeandroideveris.feature.login.data.LoginResponseData
import com.example.testeandroideveris.feature.login.data.UserAccount
import com.example.testeandroideveris.feature.login.domain.usecases.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class LoginViewModel(private val useCase: LoginUseCase) : ViewModel() {

    var dataState: MutableLiveData<LoginDataState> = MutableLiveData()
    var login = MutableLiveData<Resource<UserAccount>>()

    fun getLastUser() = liveData{ emit(useCase.getLastUser()) }

    fun login(user: String, password: String) {
        validateValues(user, password)
        doLogin(user, password)
    }

    private fun validateValues(user: String, password: String) {
        dataState.value = useCase.validate(LoginRequestData(user, password))
    }

    private fun doLogin(user: String, password: String) {
        if (dataState.value == LoginDataState.VALID_SUCCESS) {
            viewModelScope.launch {
                useCase.login(LoginRequestData(user, password))
                    .onStart { login.value = Resource.loading(data = null) }
                    .catch { exception -> login.value = Resource.error(
                        data = null,
                        message = exception.message ?: "Error Occurred!"
                    )}
                    .collect { response -> login.value = Resource.success(data = response) }
            }
        }
    }

}