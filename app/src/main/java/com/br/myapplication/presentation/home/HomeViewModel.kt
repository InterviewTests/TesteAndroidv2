package com.br.myapplication.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.myapplication.data.model.ResponseStatement
import com.br.myapplication.data.model.UserAccount
import com.br.myapplication.data.service.ApiResult
import com.br.myapplication.domain.usecase.HomeUseCase
import com.br.myapplication.domain.usecase.UserUseCase

class HomeViewModel(private val homeUserCase: HomeUseCase,
                    private val userUseCase: UserUseCase) : ViewModel() {

    val liveDataResponse: MutableLiveData<ApiResult<ResponseStatement>> = MutableLiveData()

    fun getStatements() {
        getUser()?.let { user ->
            homeUserCase(HomeUseCase.Params(user.userId),{

            })
        }
    }

    fun getUser() : UserAccount? = userUseCase.getUser()

    fun logout() = userUseCase.deleteUser()
}