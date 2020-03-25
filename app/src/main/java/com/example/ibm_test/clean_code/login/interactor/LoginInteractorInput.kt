package com.example.ibm_test.clean_code.login.interactor

interface LoginInteractorInput{
    fun validateCredentials(user: String, password: String)
    fun executeLogin(user: String, password: String)
    fun startApp()
}