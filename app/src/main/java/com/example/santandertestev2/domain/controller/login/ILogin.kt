package com.example.santandertestev2.domain.controller.login

import com.example.santandertestev2.domain.model.controller.UserAccount

interface ILogin {
    fun onLoginSuccessfull(user: UserAccount)
    fun onLoginError()
}