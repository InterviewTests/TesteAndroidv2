package com.br.myapplication.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.myapplication.helper.AppHelper
import com.br.myapplication.helper.SharedPreferencesManager
import com.br.myapplication.model.Statement
import com.br.myapplication.model.UserAccount
import com.br.myapplication.service.ApiResult
import com.br.myapplication.usercase.HomeUserCase

class HomeViewModel(private val homeUserCase: HomeUserCase,
                    private val sharedPreferencesManager: SharedPreferencesManager) : ViewModel() {

    val liveDataResponse: MutableLiveData<ApiResult<List<Statement>>> = MutableLiveData()

    fun getStatements() {
        getUser()?.let { user ->
            homeUserCase(HomeUserCase.Params(user.userId)) {
                liveDataResponse.value = it
            }
        }
    }

    fun getUser() : UserAccount? {
        val userString = sharedPreferencesManager.retrieveUser()
        return userString?.let {
            return AppHelper.convertStringToObj(it, UserAccount::class.java)
        }
    }

    fun logout() {
        sharedPreferencesManager.clearPreferences()
    }
}