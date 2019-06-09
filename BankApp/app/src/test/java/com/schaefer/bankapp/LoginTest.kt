package com.schaefer.bankapp

import android.content.Context
import com.schaefer.bankapp.login_screen.interactor.LoginInteractorInput
import com.schaefer.bankapp.model.ErrorResult
import com.schaefer.bankapp.model.login.LoginModel
import com.schaefer.bankapp.model.user.UserModel
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.mock

class LoginTest : LoginInteractorInput.FinishedListener {
    @Test
    fun makeLoginTest() {
        val user = "teste"
        val password = "teste"

        val interactor = LoginInteractorSpy()
        interactor.makeLogin(LoginModel(user, password), mock(Context::class.java), this)
    }

    @Test
    fun userLoggedTest() {
        val interactor = LoginInteractorSpy()
        interactor.checkLastUser(mock(Context::class.java), this)
    }

    override fun noUserLogged() {

    }

    override fun hasUserLogged(loginModel: LoginModel) {
        assertTrue(true)
    }

    override fun successLogin(userModel: UserModel) {
        assertTrue(true)
    }

    override fun errorLogin(errorResult: ErrorResult) {
    }

    override fun genericError(message: String) {
    }
}