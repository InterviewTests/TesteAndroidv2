package com.tata.bank.login

interface LoginInteractorInput {
//    fun fetchLoginMetaData(request: LoginRequest)
}

class LoginInteractor: LoginInteractorInput {

    lateinit var output: LoginPresenterInput
}