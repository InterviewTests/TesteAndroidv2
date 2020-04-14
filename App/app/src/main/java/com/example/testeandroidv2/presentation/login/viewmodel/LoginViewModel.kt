package com.example.testeandroidv2.presentation.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testeandroidv2.R
import com.example.testeandroidv2.data.repository.login.LoginRepository
import com.example.testeandroidv2.data.repository.login.LoginResult
import com.example.testeandroidv2.domain.login.UserAccount
import com.example.testeandroidv2.domain.login.User
import com.example.testeandroidv2.utilHelper.Constants
import com.example.testeandroidv2.utilHelper.UtilHelper

class LoginViewModel(private val dataSource: LoginRepository) : ViewModel() {

    val loginLiveData: MutableLiveData<UserAccount> = MutableLiveData()
    val viewFlipperLoginLiveData: MutableLiveData<Pair<Int, Int?>?> = MutableLiveData()

    fun login(user: User){

        val utilHelper = UtilHelper()
        if (!utilHelper.checkPassword(user.password) || !utilHelper.checkUser(user.user)) {
            viewFlipperLoginLiveData.value = Pair(Constants.VIEW_FLIPPER_ERRO, R.string.login_error_invalid_data)
        }
        else {

            dataSource.login(user) { result: LoginResult ->
                when(result) {
                    is LoginResult.Success -> {
                        loginLiveData.value = result.userAccounts
                        viewFlipperLoginLiveData.value = Pair(Constants.VIEW_FLIPPER_SUCCESS, null)
                    }
                    is LoginResult.ApiError -> {
                        if (result.statusCode in 400..499) {
                            viewFlipperLoginLiveData.value = Pair(Constants.VIEW_FLIPPER_ERRO, R.string.statements_error_400)
                        }
                    }
                    is LoginResult.ServerError -> {
                        viewFlipperLoginLiveData.value = Pair(Constants.VIEW_FLIPPER_ERRO, R.string.statements_error_500)
                    }
                }
            }
        }
    }

    class ViewModelFactory(private val dataSource: LoginRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}