package com.schaefer.bankapp

import android.content.Context
import com.schaefer.bankapp.login_screen.interactor.LoginInteractorInput
import com.schaefer.bankapp.model.login.LoginModel
import com.schaefer.bankapp.model.user.UserModel

class LoginInteractorSpy : LoginInteractorInput {
    override fun checkLastUser(context: Context, finishedListener: LoginInteractorInput.FinishedListener) {
        finishedListener.hasUserLogged(LoginModel("teste", "teste"))
    }

    override fun makeLogin(
        request: LoginModel,
        context: Context,
        finishedListener: LoginInteractorInput.FinishedListener
    ) {
        finishedListener.successLogin(
            UserModel(
                200.0F,
                2,
                "2019",
                "Artur Teste",
                "123456"
            )
        )
    }
}