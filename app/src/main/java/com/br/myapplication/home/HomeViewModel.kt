package com.br.myapplication.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.myapplication.helper.AppHelper
import com.br.myapplication.helper.SharedPreferencesManager
import com.br.myapplication.model.ResponseStatement
import com.br.myapplication.model.UserAccount
import com.br.myapplication.service.ApiResult
import com.br.myapplication.usecase.HomeUseCase
import com.br.myapplication.usecase.UserUseCase

class HomeViewModel(private val homeUserCase: HomeUseCase,
                    private val userUseCase: UserUseCase) : ViewModel() {

    val liveDataResponse: MutableLiveData<ApiResult<ResponseStatement>> = MutableLiveData()

    fun getStatements() {
        getUser()?.let { user ->
            homeUserCase(HomeUseCase.Params(user.userId), liveDataResponse)
        }
    }

    fun getUser() : UserAccount? = userUseCase.getUser()

    fun logout() = userUseCase.deleteUser()
}