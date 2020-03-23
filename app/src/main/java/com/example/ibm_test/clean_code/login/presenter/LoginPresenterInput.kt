package com.example.ibm_test.clean_code.login.presenter

import com.example.ibm_test.clean_code.login.ui.LoginActivityInput

interface LoginPresenterInput{
    fun attachLoginInput(loginActivityInput: LoginActivityInput)
    fun setDataCredentials(user: String, password: String)
}