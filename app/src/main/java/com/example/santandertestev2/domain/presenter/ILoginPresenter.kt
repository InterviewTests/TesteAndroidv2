package com.example.santandertestev2.domain.presenter

import android.content.Intent

interface ILoginPresenter {
    fun onLoginSuccessfull(intent: Intent)
    fun onLoginError(msg: String)
}