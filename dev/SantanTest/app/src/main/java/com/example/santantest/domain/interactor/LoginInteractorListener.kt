package com.example.santantest.domain.interactor

import com.example.santantest.domain.model.UserAccount

interface LoginInteractorListener {

    fun onLoginSuccess(user: UserAccount)
    fun onLoginError()

}