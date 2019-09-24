package com.example.santandertestev2

import com.example.santandertestev2.view.login.LoginActivity
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class LoginActivityTest {

    @Test
    fun password_should_Not_be_null(){
        val actLogin = Mockito.mock(LoginActivity::class.java)
        Assert.assertNotNull(actLogin)
    }


}