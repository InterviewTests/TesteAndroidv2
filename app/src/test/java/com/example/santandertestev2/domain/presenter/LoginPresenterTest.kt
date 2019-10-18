package com.example.santandertestev2.domain.presenter

import android.content.Context
import android.content.Intent
import com.example.santandertestev2.view.login.LoginActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class LoginPresenterTest {

    var loginPresenter : LoginPresenter? = null
    val context = Mockito.mock(Context::class.java)
    val view = Mockito.mock(LoginActivity::class.java)
    val intent = Intent()

    @Before
    fun prepare(){
        Mockito.`when`(view.onLoginSuccessfull(intent)).then {
            context.startActivity(intent)
        }
        loginPresenter = LoginPresenter(view)
    }

    @Test
    fun function_godetail_should_call_onLoginSuccessfull_method(){

        Assert.assertNotNull(intent)
        loginPresenter?.goDetail(intent)
        Mockito.verify(view).onLoginSuccessfull(intent)

    }


}