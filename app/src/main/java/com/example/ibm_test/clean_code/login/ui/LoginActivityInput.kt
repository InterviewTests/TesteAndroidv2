package com.example.ibm_test.clean_code.login.ui

import com.example.ibm_test.data.UserInfoData

interface LoginActivityInput {
    fun setupErrorToFieldPassword(text: String)
    fun setupErrorToFieldUser(text: String)
    fun loadingLogin(user: String, password: String)
    fun messageError(message: String)
    fun startActivityHome(userInfoData: UserInfoData)
}