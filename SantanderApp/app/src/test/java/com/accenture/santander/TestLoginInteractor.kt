package com.accenture.santander

import android.view.View
import com.accenture.santander.ServiceTest.TestServiceLogin
import com.accenture.santander.index.IndexActivity
import com.accenture.santander.login.LoginContracts
import com.accenture.santander.login.LoginInteractor
import com.accenture.santander.login.LoginPresenter
import com.accenture.santander.viewmodel.User
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TestLoginInteractor {

    lateinit var loginInteractor: LoginInteractor

    @Mock
    lateinit var iLoginInteractorOutput: LoginContracts.LoginInteractorOutput

    @Before
    fun setup() {
        val activity = Robolectric.setupActivity(IndexActivity::class.java)
        MockitoAnnotations.initMocks(this)
        loginInteractor = LoginInteractor(activity, iLoginInteractorOutput, TestServiceLogin())
        Assert.assertNotNull(loginInteractor)
    }

    @Test
    fun loginService() {
        val user = User()
        user.login = "test_user@hotmail.com"
        user.password = "Test@"

        loginInteractor.login(user)

        Mockito.verify(iLoginInteractorOutput, Mockito.times(1)).sucessLogin()
    }
}