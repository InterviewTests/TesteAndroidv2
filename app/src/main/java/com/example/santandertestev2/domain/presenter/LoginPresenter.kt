package com.example.santandertestev2.domain.presenter

import android.content.Intent

class LoginPresenter(private val activity: ILoginPresenter) {

    fun showLoginError(msg : String){
        activity.onLoginError(msg)
    }

    fun goDetail(intent: Intent){
        activity.onLoginSuccessfull(intent)
    }

}