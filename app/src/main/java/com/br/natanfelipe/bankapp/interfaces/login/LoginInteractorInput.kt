package com.br.natanfelipe.bankapp.interfaces.login

import com.br.natanfelipe.bankapp.view.login.LoginRequest

interface LoginInteractorInput {
    fun fetchLoginMetaData(loginRequest: LoginRequest, username: String, pwd: String)
    fun validateFieldsData(username: String, pwd: String) : Boolean
    fun validateUsernameData(username: String) : Boolean
    fun validatePwdData(pwd: String) : Boolean
}