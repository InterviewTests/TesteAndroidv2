package com.example.ibm_test.clean_code.login.presenter

import com.example.ibm_test.clean_code.login.ui.LoginActivityInput
import com.example.ibm_test.data.UserInfoData

interface LoginPresenterInput{
    fun attachLoginInput(loginActivityInput: LoginActivityInput)
    fun setDataCredentials(user: String, password: String)
    fun onSuccess(userInfoData: UserInfoData)
    fun onError(error: Throwable)
}