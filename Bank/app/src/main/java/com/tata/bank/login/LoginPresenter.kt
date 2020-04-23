package com.tata.bank.login

import java.lang.ref.WeakReference

interface LoginPresenterInput {
//    fun presentHomeMetaData(response: LoginResponse)
}

class LoginPresenter: LoginPresenterInput {
    lateinit var output: WeakReference<LoginActivityInput>
}