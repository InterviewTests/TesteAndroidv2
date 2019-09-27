package com.example.santandertestev2

import com.example.santandertestev2.domain.controller.login.LoginController
import com.example.santandertestev2.domain.presenter.LoginPresenter
import com.example.santandertestev2.view.login.LoginActivity
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class LoginActivityTest {

    val loginPresenter = Mockito.mock(LoginPresenter::class.java)

    @Test
    fun password_should_Not_be_null(){

      assert(true)

    }


}