package com.br.natanfelipe.bankapp.login

import android.content.Intent
import com.br.natanfelipe.bankapp.api.RestApi
import com.br.natanfelipe.bankapp.router.LoginRouter
import com.br.natanfelipe.bankapp.view.home.HomeActivity
import com.br.natanfelipe.bankapp.view.login.LoginActivity
import com.br.natanfelipe.bankapp.view.login.LoginViewModel
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class LoginRouterTest {

    lateinit var loginActivityMock: LoginActivity
    lateinit var api: RestApi
    lateinit var loginRouter: LoginRouter
    lateinit var intent: Intent


    @Before
    fun setUp() {
        loginRouter = LoginRouter()
        loginActivityMock = Mockito.mock(LoginActivity::class.java)
        api = RestApi()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun goToHomeActivity(){
        var loginViewModel = LoginViewModel()
        loginViewModel.userAccount.name = "Teste"
        loginViewModel.userAccount.agency = 1111
        loginViewModel.userAccount.bankAccount = "Any"
        loginViewModel.userAccount.balance = 2000.0
        loginViewModel.userAccount.userId = 1
        loginActivityMock.router = loginRouter
        intent = loginRouter.determineNextScreen(loginActivityMock)
        val targetActivityName = intent.component.className
        Assert.assertEquals(targetActivityName,HomeActivity::class.java)
    }
}