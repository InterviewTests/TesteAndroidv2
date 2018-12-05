package com.example.otavioaugusto.testesantander

import com.example.otavioaugusto.testesantander.login.LoginActivity
import com.example.otavioaugusto.testesantander.login.LoginPresenter
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import org.mockito.Mockito

class TesteLogin {
    val loginViewMock: LoginActivity = Mockito.mock(LoginActivity::class.java)


    @Test
    fun emailOUCPF(){

        val mock = mock<LoginActivity> {
        }

        //given
        val objeto = LoginPresenter(loginViewMock)
        val login = "otavio@gmail.com"
        val password = "Otavio1"


        //when
        val result = objeto.validar(login, password)
        //then
        result.compareTo(true)

    }
}